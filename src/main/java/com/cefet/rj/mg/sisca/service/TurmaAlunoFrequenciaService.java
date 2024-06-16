package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.turmaAlunoFrequencia.TurmaAlunoFrequencia;
import com.cefet.rj.mg.sisca.domain.turmaAlunoFrequencia.TurmaAlunoFrequenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class TurmaAlunoFrequenciaService {

    @Autowired
    private TurmaAlunoFrequenciaRepository turmaAlunoFrequenciaRepository;

    public TurmaAlunoFrequencia cadastrarFrequencia(TurmaAlunoFrequencia turmaAlunoFrequencia) {
        return turmaAlunoFrequenciaRepository.save(turmaAlunoFrequencia);
    }

    public TurmaAlunoFrequencia pegarFalta(Long idTurma, Long idAluno, Date falta) {
        return turmaAlunoFrequenciaRepository.buscarTurmaPorIdEData(idTurma, idAluno, falta)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public void deletarFrequencia(TurmaAlunoFrequencia turmaAlunoFrequencia) {
        turmaAlunoFrequenciaRepository.delete(turmaAlunoFrequencia);
    }

}
