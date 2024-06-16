package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.turmaAlunoNota.TurmaAlunoNota;
import com.cefet.rj.mg.sisca.domain.turmaAlunoNota.TurmaAlunoNotaId;
import com.cefet.rj.mg.sisca.domain.turmaAlunoNota.TurmaAlunoNotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TurmaAlunoNotaService {

    @Autowired
    private TurmaAlunoNotaRepository turmaAlunoNotaRepository;

    public TurmaAlunoNota  salvarAlunoENota(TurmaAlunoNota turmaAlunoNota) {
        return turmaAlunoNotaRepository.save(turmaAlunoNota);
    }

    @Transactional
    public TurmaAlunoNota encontrarRegistro(TurmaAlunoNotaId turmaAlunoNotaId) {
        TurmaAlunoNota turmaAlunoNota = turmaAlunoNotaRepository.findById(turmaAlunoNotaId)
                .orElseThrow(() -> new RuntimeException("Aluno ou Turma não encontrado"));

        return turmaAlunoNota;
    }

}
