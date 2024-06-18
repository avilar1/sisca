

CREATE TABLE USUARIO (
    id_usuario BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefone VARCHAR(50) NOT NULL,
    data_nascimento TIMESTAMP NOT NULL,
    ativo tinyint NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- USAR ENUM, MANTER STANDBY
CREATE TABLE ROLE (
    id_role BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

-- USAR ENUM, MANTER STANDBY
CREATE TABLE USUARIO_ROLE (
    id_usuario BIGINT NOT NULL,
    id_role BIGINT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario),
    FOREIGN KEY (id_role) REFERENCES ROLE(id_role)
);

CREATE TABLE ALUNO (
    id_aluno BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    matricula_aluno INT NOT NULL UNIQUE,
    status int NOT NULL,
    ano_matricula VARCHAR(6),
    valor_mensalidade DECIMAL(10, 2),
    id_usuario BIGINT,
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario)
);

CREATE TABLE CURSO (
    id_curso BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    periodos INT NOT NULL
);

CREATE TABLE MATERIA (
    id_materia BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE CURSO_GRADE (
    id_curso BIGINT NOT NULL,
    id_materia BIGINT NOT NULL,
    periodo INT NOT NULL,
    FOREIGN KEY (id_curso) REFERENCES CURSO(id_curso),
    FOREIGN KEY (id_materia) REFERENCES MATERIA(id_materia)
);


# SERÁ O ENUM DE USUÁRIO, DEIXAR STANDBY
CREATE TABLE TIPO_FUNCIONARIO (
    id_tipo_funcionario BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    cargo VARCHAR(100) NOT NULL
);

CREATE TABLE FUNCIONARIO (
    id_funcionario BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    tipo_funcionario VARCHAR(50) NOT NULL,
    matricula_funcionario INT,
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario)
);

CREATE TABLE TURMA (
    id_turma BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    id_funcionario BIGINT NOT NULL,
    id_materia BIGINT NOT NULL,
    semestre_turma VARCHAR(6) NOT NULL,
    FOREIGN KEY (id_funcionario) REFERENCES FUNCIONARIO(id_funcionario),
    FOREIGN KEY (id_materia) REFERENCES MATERIA(id_materia)
);

CREATE TABLE STATUS_ALUNO_TURMA (
    id_status_aluno_turma BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE TURMA_ALUNO (
    id_turma BIGINT NOT NULL,
    status_aluno_turma VARCHAR(50) NOT NULL,
    id_aluno BIGINT NOT NULL,
    FOREIGN KEY (id_turma) REFERENCES TURMA(id_turma),
    FOREIGN KEY (id_aluno) REFERENCES ALUNO (id_aluno)
);

CREATE TABLE TURMA_ALUNO_NOTA (
    id_turma BIGINT NOT NULL,
    id_aluno BIGINT NOT NULL,
    p1 FLOAT,
    p2 FLOAT,
    pf FLOAT,
    FOREIGN KEY (id_turma) REFERENCES TURMA(id_turma),
    FOREIGN KEY (id_aluno) REFERENCES ALUNO (id_aluno)
);

CREATE TABLE TURMA_ALUNO_FREQUENCIA (
    id_turma BIGINT NOT NULL,
    id_aluno BIGINT NOT NULL,
    faltou DATE,
    FOREIGN KEY (id_turma) REFERENCES TURMA(id_turma),
    FOREIGN KEY (id_aluno) REFERENCES ALUNO (id_aluno)
);


CREATE TABLE ALUNO_CURSO (
    id_curso BIGINT NOT NULL,
    ano_matricula VARCHAR(6) NOT NULL,
    id_aluno BIGINT,
    FOREIGN KEY (id_curso) REFERENCES CURSO(id_curso),
    FOREIGN KEY (id_aluno) REFERENCES ALUNO(id_aluno)
);



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