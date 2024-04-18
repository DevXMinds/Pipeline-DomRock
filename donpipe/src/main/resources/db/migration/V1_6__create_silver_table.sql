CREATE TABLE IF NOT EXISTS API_BD3.SILVER
(
    id_user          INT NOT NULL,
    FOREIGN KEY (id_user) REFERENCES API_BD3.USER (id),
    id_arquivo       INT NOT NULL,
    FOREIGN KEY (id_arquivo) REFERENCES API_BD3.BRONZE (id_bronze),
    tipagem          VARCHAR,
    id_silver        SERIAL PRIMARY KEY,
    data_modificacao DATE,
    PK               VARCHAR,
    notdeletable     VARCHAR,
    yaml             TEXT,
    hash_            VARCHAR
);

DO
$$BEGIN
    CREATE TRIGGER hash_trigger
        BEFORE INSERT ON API_BD3.SILVER
        FOR EACH ROW
    EXECUTE FUNCTION generate_hash();
EXCEPTION
    WHEN duplicate_object THEN
        NULL;
END;$$;


