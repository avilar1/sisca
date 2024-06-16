package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.alunoTurma.AlunoTurma;
import com.cefet.rj.mg.sisca.domain.alunoTurma.AlunoTurmaRepository;
import com.cefet.rj.mg.sisca.domain.alunoTurma.DadosDetalhamentoAlunoTurma;
import com.cefet.rj.mg.sisca.domain.alunoTurma.TurmaAlunoId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoTurmaService {

        @Autowired
        private AlunoTurmaRepository alunoTurmaRepository;
    public AlunoTurma salvarAlunoTurma(AlunoTurma alunoTurma) {
        return alunoTurmaRepository.save(alunoTurma);
    }

    public List<DadosDetalhamentoAlunoTurma> buscarAlunosPorTurma(Long id) {
        return alunoTurmaRepository.findByIdTurma(id).stream()
                .map(DadosDetalhamentoAlunoTurma::new)
                .collect(Collectors.toList());
    }

    public AlunoTurma buscarAlunoPorTurma(Long id_aluno, Long id_turma) {
        TurmaAlunoId turmaAlunoId = new TurmaAlunoId(id_aluno, id_turma);

        return alunoTurmaRepository.findById(turmaAlunoId)
                .orElseThrow(() -> new RuntimeException("Turma ou aluno não encontrada"));
    }

    public AlunoTurma trancarMatricula(AlunoTurma alunoTurma) {
        alunoTurma.trancarMatricula();

        alunoTurmaRepository.save(alunoTurma);

        return alunoTurma;
    }

    public void deletarAlunoTurma(AlunoTurma alunoTurma) {
        alunoTurmaRepository.delete(alunoTurma);
    }


//
//    public DadosSituacaoAlunoTurma obterSituacaoAlunoTurma(Long alunoId, Long turmaId) {
//        List<AlunoTurma> alunoTurmaList = alunoTurmaRepository.findByAlunoIdAndTurmaId(alunoId, turmaId);
//        if (alunoTurmaList.isEmpty()) {
//            throw new RuntimeException("Situação não encontrada");
//        }
//
//        AlunoTurma alunoTurma = alunoTurmaList.get(0);
//        Aluno aluno = alunoTurma.getAluno();
//        Turma turma = alunoTurma.getTurma();
//        String situacao = alunoTurma.getSituacao();
//
//
//        return new DadosSituacaoAlunoTurma(alunoTurma);
//    }
//
//    public DadosNotaAlunoTurma obterNotaAlunoTurma(Long alunoId, Long turmaId) {
//        List<AlunoTurma> alunoTurmaList = alunoTurmaRepository.findByAlunoIdAndTurmaId(alunoId, turmaId);
//        if (alunoTurmaList.isEmpty()) {
//            throw new RuntimeException("Nota não encontrada");
//        }
//
//        AlunoTurma alunoTurma = alunoTurmaList.get(0);
//        Aluno aluno = alunoTurma.getAluno();
//        Turma turma = alunoTurma.getTurma();
//        List<Nota> notas = alunoTurma.getNotas();
//
//
//        return new DadosNotaAlunoTurma(alunoTurma);
//    }
//
//    public DadosFrequenciaAlunoTurma obterFrequenciaAlunoTurma(Long alunoId, Long turmaId) {
//        List<AlunoTurma> alunoTurmaList = alunoTurmaRepository.findByAlunoIdAndTurmaId(alunoId, turmaId);
//        if (alunoTurmaList.isEmpty()) {
//            throw new RuntimeException("Nota não encontrada");
//        }
//
//        AlunoTurma alunoTurma = alunoTurmaList.get(0);
//        Aluno aluno = alunoTurma.getAluno();
//        Turma turma = alunoTurma.getTurma();
//        List<Frequencia> frequencias = alunoTurma.getFrequencias();
//
//
//        return new DadosFrequenciaAlunoTurma(alunoTurma);
//    }

}
