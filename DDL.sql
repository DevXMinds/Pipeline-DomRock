/*
 *
 * Tabelas:
 * - API_BD3.USER
 * - API_BD3.EMPRESA
 * - API_BD3.ARQUIVO
 * - API_BD3.LZ
 * - API_BD3.BRONZE
 * - API_BD3.SILVER
 * - API_BD3.LOGs
 *
 * Extenções:
 * - pgcrypto: Criptografa campos
 *
 * Constraints:
 * - CK_ARQUIVO_ESTAGIO_STATUS: checa a integridade de colunas estagio e estatus
 *
 * Triggers:
 * - hash_trigger
 * - 
 * Functions:
 * - generate_hash(): Gera um hash para a tabela API_BD3.BRONZE e API_BD3.SILVER
 * - encrypt_password(): Encripta a coluna "senha" da tabela API_BD3.USER
 */
CREATE DATABASE don_pipeline;

ALTER USER postgres WITH PASSWORD 'postgres';
GRANT ALL PRIVILEGES ON DATABASE don_pipeline TO postgres;

CREATE SCHEMA API_BD3;

-- encriptador pgcrypto
CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE API_BD3.USER (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    id_empresa INT NOT NULL,
    FOREIGN KEY (id_empresa) REFERENCES API_BD3.EMPRESA(id),
    setor VARCHAR(100) NOT NULL
);
-- Encripta a coluna senha usando pgcrypto
CREATE OR REPLACE FUNCTION encrypt_password()
RETURNS TRIGGER AS $$
BEGIN
    NEW.senha := crypt(NEW.senha, gen_salt('bf'));
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER encrypt_trigger
BEFORE INSERT OR UPDATE OF senha ON API_BD3.USER
FOR EACH ROW
EXECUTE FUNCTION encrypt_password();


CREATE TABLE API_BD3.EMPRESA (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    setor VARCHAR(100) NOT NULL
);

CREATE TABLE API_BD3.ARQUIVO (
    id SERIAL PRIMARY KEY,
    id_user INT NOT NULL,
    FOREIGN KEY (id_user) REFERENCES API_BD3.USER(id),
    id_empresa INT NOT NULL,
    FOREIGN KEY (id_empresa) REFERENCES API_BD3.EMPRESA(id),
    arquivo_json VARCHAR NOT NULL,
    nome_arquivo VARCHAR(100),
    data_criacao DATE,
    estagio VARCHAR,
    estatus VARCHAR
);
CREATE OR REPLACE FUNCTION set_data_criacao_modificacao()
RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
        NEW.data_criacao := CURRENT_DATE;
    END IF;
    IF TG_OP = 'INSERT' OR TG_OP = 'UPDATE' THEN
        NEW.data_modificacao := CURRENT_TIMESTAMP;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER set_data_criacao_modificacao_trigger
BEFORE INSERT OR UPDATE ON API_BD3.ARQUIVO
FOR EACH ROW
EXECUTE FUNCTION set_data_criacao_modificacao();

ALTER TABLE API_BD3.ARQUIVO
ADD CONSTRAINT CK_ARQUIVO_ESTAGIO_STATUS
CHECK (estagio IN ('lz', 'bronze', 'silver') AND estatus IN ('concluído', 'em andamento', 'cancelado'));


CREATE TABLE API_BD3.LZ(
    id_arquivo INT NOT NULL,
    FOREIGN KEY (id_arquivo) REFERENCES API_BD3.ARQUIVO(id),
    data DATE, 
    PK VARCHAR PRIMARY KEY,
    notdeletable VARCHAR
);

CREATE TABLE API_BD3.BRONZE(
    id_user INT NOT NULL,
    FOREIGN KEY (id_user) REFERENCES API_BD3.USER(id),
    id_arquivo INT NOT NULL,
    FOREIGN KEY (id_arquivo) REFERENCES API_BD3.ARQUIVO(id),
    id_bronze SERIAL PRIMARY KEY,
    data_modificacao DATE,
    PK VARCHAR,
    naodeletavel VARCHAR,
    hash_ VARCHAR,
    tipagem_bronze VARCHAR
);

-- função que será executada pela trigger
CREATE OR REPLACE FUNCTION generate_hash()
RETURNS TRIGGER AS $$
BEGIN
    NEW.hash_ := md5(NEW.id_user || NEW.id_bronze || NEW.data_modificacao::text);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


-- Cria a trigger que chama a função quando um novo registro é inserido na tabela API_BD3.BRONZE
CREATE TRIGGER hash_trigger
BEFORE INSERT ON API_BD3.BRONZE
FOR EACH ROW
EXECUTE FUNCTION generate_hash();


CREATE TABLE API_BD3.SILVER(
    id_user INT NOT NULL,
    FOREIGN KEY (id_user) REFERENCES API_BD3.USER(id),
    id_arquivo INT NOT NULL,
    FOREIGN KEY (id_arquivo) REFERENCES API_BD3.BRONZE(id_bronze),
    tipagem VARCHAR,
    id_silver SERIAL PRIMARY KEY,
    data_modificacao DATE, 
    PK VARCHAR,
    notdeletable VARCHAR,
    yaml TEXT,
    hash_ VARCHAR
);

CREATE TRIGGER hash_trigger
BEFORE INSERT ON API_BD3.SILVER
FOR EACH ROW
EXECUTE FUNCTION generate_hash();


CREATE TABLE API_BD3.LOG (
    id_user INT NOT NULL,
    FOREIGN KEY (id_user) REFERENCES API_BD3.USER(id),
    id_arquivo INT NOT NULL,
    FOREIGN KEY (id_arquivo) REFERENCES API_BD3.ARQUIVO(id),
    id_log SERIAL PRIMARY KEY,
    data_insercao DATE,
    data_edicao DATE,
    estagio VARCHAR
);
