

CREATE TABLE SALARIO (
    id_salario BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    id_funcionario BIGINT NOT NULL,
    valor FLOAT NOT NULL,
    data_criacao DATE NOT NULL,
    FOREIGN KEY (id_funcionario) REFERENCES FUNCIONARIO(id_funcionario)
);

CREATE TABLE FOLHA_DE_PAGAMENTO (
    id_folha_de_pagamento BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    id_funcionario_pagante BIGINT NOT NULL,
    valor_pago FLOAT NOT NULL,
    data_pagamento DATE NOT NULL,
    FOREIGN KEY (id_funcionario_pagante) REFERENCES FUNCIONARIO(id_funcionario)
);

CREATE TABLE LIVRO (
    id_livro BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(150) NOT NULL,
    editor VARCHAR(150) NOT NULL
);

CREATE TABLE EMPRESTIMO (
    id_emprestimo BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    id_livro BIGINT NOT NULL,
    id_usuario BIGINT NOT NULL,
    emprestado DATE NOT NULL,
    limite_emprestimo DATE NOT NULL,
    data_entrega DATE,
    FOREIGN KEY (id_livro) REFERENCES LIVRO(id_livro),
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario)
);

CREATE TABLE MENSALIDADE_ALUNO (
    id_mensalidade_aluno BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    id_aluno INT NOT NULL,
    valor FLOAT NOT NULL,
    data_criacao DATE NOT NULL,
    FOREIGN KEY (id_aluno) REFERENCES ALUNO(matricula_aluno)
);

CREATE TABLE TIPO_PRODUTO (
    id_tipo_produto BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE PRODUTO (
    id_produto BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    id_tipo_produto BIGINT NOT NULL,
    FOREIGN KEY (id_tipo_produto) REFERENCES TIPO_PRODUTO(id_tipo_produto)
);

CREATE TABLE ESTOQUE (
    id_estoque BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    id_produto BIGINT NOT NULL,
    id_funcionario BIGINT NOT NULL,
    quantidade FLOAT NOT NULL,
    descricao VARCHAR(255),
    data_transacao DATE NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES PRODUTO(id_produto),
    FOREIGN KEY (id_funcionario) REFERENCES FUNCIONARIO(id_funcionario)
);

#Enum criado junto ao Boleto
CREATE TABLE TIPO_BOLETO (
    id_tipo_boleto BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE BOLETOS (
    codigo_de_barras VARCHAR(13) NOT NULL UNIQUE PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    valor FLOAT NOT NULL,
    data_criacao DATE NOT NULL,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    juros FLOAT NOT NULL,
    tipo_boleto VARCHAR(100), -- Pode ser uma string ou qualquer coisa no futuro
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario)

);