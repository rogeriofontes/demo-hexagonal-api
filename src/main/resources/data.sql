DROP TABLE IF EXISTS tb_client;

CREATE TABLE tb_client (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    nome VARCHAR(250) NOT NULL,
    conta VARCHAR(250) NOT NULL
);

INSERT INTO tb_client (nome, conta) VALUES
('Rogerio', '12345'),
('Maria', '12356'),
('Ze', '15677');