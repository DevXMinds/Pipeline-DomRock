CREATE TABLE IF NOT EXISTS API_BD3.LOG
(
    id_user       INT NOT NULL,
    FOREIGN KEY (id_user) REFERENCES API_BD3.USER (id),
    id_arquivo    INT NOT NULL,
    FOREIGN KEY (id_arquivo) REFERENCES API_BD3.ARQUIVO (id),
    id_log        SERIAL PRIMARY KEY,
    data_insercao DATE,
    data_edicao   DATE,
    estagio       VARCHAR
);