ALTER TABLE TURMA_ALUNO DROP FOREIGN KEY turma_aluno_ibfk_1;
ALTER TABLE TURMA_ALUNO DROP COLUMN matricula_aluno, DROP INDEX matricula_aluno;

ALTER TABLE TURMA_ALUNO ADD COLUMN id_aluno BIGINT NOT NULL;

ALTER TABLE TURMA_ALUNO ADD CONSTRAINT FK_turma_aluno_id_aluno FOREIGN KEY (id_aluno) REFERENCES ALUNO (id_aluno);
