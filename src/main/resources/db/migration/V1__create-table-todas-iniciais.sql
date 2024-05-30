

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
    id_tipo_funcionario BIGINT,
    matricula_funcionario INT,
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario),
    FOREIGN KEY (id_tipo_funcionario) REFERENCES TIPO_FUNCIONARIO(id_tipo_funcionario)
);

CREATE TABLE TURMA (
    id_turma BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    id_funcionario BIGINT NOT NULL,
    id_materia BIGINT NOT NULL,
    semestre_turma DATE NOT NULL,
    FOREIGN KEY (id_funcionario) REFERENCES FUNCIONARIO(id_funcionario),
    FOREIGN KEY (id_materia) REFERENCES MATERIA(id_materia)
);

CREATE TABLE STATUS_ALUNO_TURMA (
    id_status_aluno_turma BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE TURMA_ALUNO (
    matricula_aluno INT NOT NULL,
    id_turma BIGINT NOT NULL,
    id_status_aluno_turma BIGINT NOT NULL,
    FOREIGN KEY (matricula_aluno) REFERENCES ALUNO(matricula_aluno),
    FOREIGN KEY (id_turma) REFERENCES TURMA(id_turma),
    FOREIGN KEY (id_status_aluno_turma) REFERENCES STATUS_ALUNO_TURMA(id_status_aluno_turma)
);

CREATE TABLE TURMA_ALUNO_NOTA (
    matricula_aluno INT NOT NULL,
    id_turma BIGINT NOT NULL,
    p1 FLOAT,
    p2 FLOAT,
    FOREIGN KEY (matricula_aluno) REFERENCES ALUNO(matricula_aluno),
    FOREIGN KEY (id_turma) REFERENCES TURMA(id_turma)
);

CREATE TABLE TURMA_ALUNO_FREQUENCIA (
    matricula_aluno INT NOT NULL,
    id_turma BIGINT NOT NULL,
    faltou DATE,
    FOREIGN KEY (matricula_aluno) REFERENCES ALUNO(matricula_aluno),
    FOREIGN KEY (id_turma) REFERENCES TURMA(id_turma)
);

CREATE TABLE STATUS_ALUNO_CURSO (
    id_status BIGINT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE ALUNO_CURSO (
    matricula_aluno INT NOT NULL,
    id_curso BIGINT NOT NULL,
    id_status BIGINT NOT NULL,
    ano_matricula TIMESTAMP NOT NULL,
    FOREIGN KEY (matricula_aluno) REFERENCES ALUNO(matricula_aluno),
    FOREIGN KEY (id_curso) REFERENCES CURSO(id_curso),
    FOREIGN KEY (id_status) REFERENCES STATUS_ALUNO_CURSO(id_status)
);

