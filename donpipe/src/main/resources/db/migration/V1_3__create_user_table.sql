CREATE TABLE IF NOT EXISTS API_BD3.USER
(
    id         SERIAL PRIMARY KEY,
    nome_user  VARCHAR(100) NOT NULL,
    email      VARCHAR(100) NOT NULL,
    senha      VARCHAR(100) NOT NULL,
    id_empresa INT          NOT NULL,
    FOREIGN KEY (id_empresa) REFERENCES API_BD3.EMPRESA (id),
    permissao VARCHAR(20) NOT NULL,
    FOREIGN KEY (permissao) REFERENCES API_BD3.PERMISSAO (nome)
);

-- Encripta a coluna senha usando pgcrypto
CREATE OR REPLACE FUNCTION encrypt_password()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.senha := crypt(NEW.senha, gen_salt('bf'));
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DO
$$BEGIN
    CREATE TRIGGER encrypt_trigger
        BEFORE INSERT OR UPDATE OF senha ON API_BD3.USER
        FOR EACH ROW
    EXECUTE FUNCTION encrypt_password();
EXCEPTION
    WHEN duplicate_object THEN
        NULL;
END;$$;
