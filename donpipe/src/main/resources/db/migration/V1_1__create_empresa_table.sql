CREATE TABLE IF NOT EXISTS API_BD3.EMPRESA
(
    id    SERIAL PRIMARY KEY,
    nome  VARCHAR(100) NOT NULL,
    setor VARCHAR(100) NOT NULL
);