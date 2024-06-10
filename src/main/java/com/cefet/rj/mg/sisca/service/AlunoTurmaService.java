package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.alunoTurma.AlunoTurma;
import com.cefet.rj.mg.sisca.domain.alunoTurma.AlunoTurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoTurmaService {

        @Autowired
        private AlunoTurmaRepository alunoTurmaRepository;
    public AlunoTurma salvarAlunoTurma(AlunoTurma alunoTurma) {
        return alunoTurmaRepository.save(alunoTurma);
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
