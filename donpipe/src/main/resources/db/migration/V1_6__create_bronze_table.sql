CREATE TABLE IF NOT EXISTS API_BD3.BRONZE
(
    id_user          INT NOT NULL,
    FOREIGN KEY (id_user) REFERENCES API_BD3.USER (id),
    id_arquivo       INT NOT NULL,
    FOREIGN KEY (id_arquivo) REFERENCES API_BD3.ARQUIVO (id),
    id_bronze        SERIAL PRIMARY KEY,
    data_modificacao DATE,
    PK               VARCHAR,
    naodeletavel     VARCHAR,
    hash_            VARCHAR,
    tipagem_bronze   VARCHAR
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

DO
$$BEGIN
    CREATE TRIGGER hash_trigger
        BEFORE INSERT ON API_BD3.BRONZE
        FOR EACH ROW
    EXECUTE FUNCTION generate_hash();
    EXCEPTION
        WHEN duplicate_object THEN
            NULL;
END;$$;
