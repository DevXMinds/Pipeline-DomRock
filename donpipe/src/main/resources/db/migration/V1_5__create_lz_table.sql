CREATE TABLE IF NOT EXISTS API_BD3.LZ
(
    id           SERIAL PRIMARY KEY,
    id_arquivo   INT NOT NULL,
    FOREIGN KEY (id_arquivo) REFERENCES API_BD3.ARQUIVO (id),
    data         DATE,
    coluna_pk    VARCHAR,
    notdeletable VARCHAR
);
