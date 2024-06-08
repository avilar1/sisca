package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.turma.Turma;
import com.cefet.rj.mg.sisca.domain.turma.TurmaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TurmaService {

    TurmaRepository turmaRepository;

    public Optional<Turma> encontrarTurma(Long id_turma) {
        return turmaRepository.findById(id_turma);
    }

    public Turma salvarTurma(Turma novaTurma) {
        return turmaRepository.save(novaTurma);
    }
}
