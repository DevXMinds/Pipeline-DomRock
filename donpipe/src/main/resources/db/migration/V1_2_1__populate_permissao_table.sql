INSERT INTO API_BD3.PERMISSAO (nome, descricao)
VALUES ('lz', 'Recepção e tratamento dos dados para leitura do usuário, definindo os dados a serem tratados nas etapas seguintes')
ON CONFLICT DO NOTHING;

INSERT INTO API_BD3.PERMISSAO (nome, descricao)
VALUES ('bronze', 'Validação de chaves, administração de colunas e tipagem de dados')
ON CONFLICT DO NOTHING;

INSERT INTO API_BD3.PERMISSAO (nome, descricao)
VALUES ('silver', 'Validação de dados de regra de negócio e geração de arquivo de configuração YAML')
ON CONFLICT DO NOTHING;

INSERT INTO API_BD3.PERMISSAO (nome, descricao)
VALUES ('admin', 'Um super usuário com permissão de criação de novos usuários e empresas')
ON CONFLICT DO NOTHING;