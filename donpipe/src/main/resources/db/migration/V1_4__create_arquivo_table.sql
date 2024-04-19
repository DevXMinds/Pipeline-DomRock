CREATE TABLE IF NOT EXISTS API_BD3.ARQUIVO
(
    id               SERIAL PRIMARY KEY,
    id_user          INT     NOT NULL,
    FOREIGN KEY (id_user) REFERENCES API_BD3.USER (id),
    id_empresa       INT     NOT NULL,
    FOREIGN KEY (id_empresa) REFERENCES API_BD3.EMPRESA (id),
    tipo_arquivo     varchar not null,
    dados_arquivo    varchar not null,
    data_modificacao DATE,
    nome_arquivo     VARCHAR(100),
    data_criacao     DATE,
    estagio          VARCHAR,
    estatus          VARCHAR
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

DO
$$BEGIN
    CREATE TRIGGER set_data_criacao_modificacao_trigger
        BEFORE INSERT OR UPDATE ON API_BD3.ARQUIVO
        FOR EACH ROW
    EXECUTE FUNCTION set_data_criacao_modificacao();

    EXCEPTION
        WHEN duplicate_object THEN
            NULL;
END;$$;

ALTER TABLE API_BD3.ARQUIVO
    DROP CONSTRAINT IF EXISTS CK_ARQUIVO_ESTAGIO_STATUS;
ALTER TABLE API_BD3.ARQUIVO
    ADD CONSTRAINT CK_ARQUIVO_ESTAGIO_STATUS
        CHECK (estagio IN ('lz', 'bronze', 'silver') AND estatus IN ('concluído', 'em andamento', 'cancelado'));