ALTER TABLE ALUNO_CURSO DROP FOREIGN KEY ALUNO_CURSO_ibfk_3;

DROP TABLE STATUS_ALUNO_CURSO;

ALTER TABLE `sisca`.`ALUNO_CURSO`
DROP INDEX `id_status` ;