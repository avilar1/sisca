package com.cefet.rj.mg.sisca;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.aluno.AlunoRepository;
import com.cefet.rj.mg.sisca.domain.turma.Turma;
import com.cefet.rj.mg.sisca.domain.turma.TurmaRepository;
import com.cefet.rj.mg.sisca.domain.turmaAlunoFrequencia.TurmaAlunoFrequencia;
import com.cefet.rj.mg.sisca.domain.turmaAlunoFrequencia.TurmaAlunoFrequenciaId;
import com.cefet.rj.mg.sisca.domain.turmaAlunoFrequencia.TurmaAlunoFrequenciaRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class TurmaAlunoFrequenciaRepositoryTest {

    @Autowired
    private TurmaAlunoFrequenciaRepository repository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Test
    public void testSaveAndFind() {
        Aluno aluno = new Aluno();
        aluno.setId_aluno(1L);
        aluno.setNome("Teste Aluno");
        alunoRepository.save(aluno);

        Turma turma = new Turma();
        turma.setId_turma(1L);
        turmaRepository.save(turma);

        LocalDate dataFalta = LocalDate.now();
        TurmaAlunoFrequencia frequencia = new TurmaAlunoFrequencia(aluno, turma, dataFalta);
        repository.save(frequencia);

        TurmaAlunoFrequenciaId id = new TurmaAlunoFrequenciaId(aluno.getId_aluno(), turma.getId_turma());
        Optional<TurmaAlunoFrequencia> found = repository.findById(id);

        Assert.assertTrue(found.isPresent());
        Assertions.assertEquals(dataFalta, found.get().getFaltou());
    }
}
