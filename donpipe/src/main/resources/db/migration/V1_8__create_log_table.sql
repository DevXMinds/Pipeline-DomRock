CREATE TABLE IF NOT EXISTS API_BD3.LOG
(
    id_log SERIAL PRIMARY KEY,
    id_user INT NOT NULL,
    FOREIGN KEY (id_user) REFERENCES API_BD3.USER (id),
    log_date date,
    id_arquivo INT,
    FOREIGN KEY (id_arquivo) REFERENCES API_BD3.ARQUIVO (id)
);