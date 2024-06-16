ALTER TABLE TURMA_ALUNO_NOTA DROP FOREIGN KEY turma_aluno_nota_ibfk_1;
ALTER TABLE TURMA_ALUNO_NOTA DROP INDEX matricula_aluno;

ALTER TABLE TURMA_ALUNO_NOTA DROP COLUMN matricula_aluno;

ALTER TABLE TURMA_ALUNO_NOTA ADD COLUMN id_aluno BIGINT NOT NULL, ADD CONSTRAINT fk_id_aluno FOREIGN KEY (id_aluno) REFERENCES ALUNO (id_aluno);

ALTER TABLE TURMA_ALUNO_NOTA ADD COLUMN pf FLOAT;