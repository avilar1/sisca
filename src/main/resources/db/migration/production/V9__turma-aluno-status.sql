ALTER TABLE TURMA_ALUNO DROP FOREIGN KEY TURMA_ALUNO_ibfk_3;
ALTER TABLE TURMA_ALUNO DROP INDEX id_status_aluno_turma;

ALTER TABLE TURMA_ALUNO CHANGE COLUMN id_status_aluno_turma status_aluno_turma VARCHAR(50) NOT NULL;