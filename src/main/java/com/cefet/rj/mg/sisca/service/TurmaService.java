package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.professor.Professor;
import com.cefet.rj.mg.sisca.domain.professor.ProfessorService;
import com.cefet.rj.mg.sisca.domain.turma.DadosDetalhamentoTurma;
import com.cefet.rj.mg.sisca.domain.turma.Turma;
import com.cefet.rj.mg.sisca.domain.turma.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurmaService {
    @Autowired
    TurmaRepository turmaRepository;

    @Autowired
    private ProfessorService professorService;

    public Optional<Turma> encontrarTurma(Long id_turma) {
        return turmaRepository.findById(id_turma);
    }

    public Turma salvarTurma(Turma novaTurma) {
        Professor professor = professorService.listarProfessor(novaTurma.getProfessor().getId_funcionario());
        if (!professor.isProfessor(professor.getId_funcionario())) {
            throw new IllegalArgumentException("Somente funcionários com a role de PROFESSOR podem ser usados para cadastro de uma turma.");
        }

        return turmaRepository.save(novaTurma);
    }

    public Turma pegarUmaTurma(Long id) {
        Turma turmaExiste = turmaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma não existe"));

        return turmaExiste;
    }

    public List<Turma> turmaPorPeriodo(String periodo) {
        List<Turma> turmas = turmaRepository.findAllBySemestreTurma(periodo);

        if (turmas.isEmpty()) {
            throw new RuntimeException("Não existem turmas nesse período");
        }

        return turmas;
    }

    public List<DadosDetalhamentoTurma> listarTodas() {
        return turmaRepository.findAll().stream()
                .map(DadosDetalhamentoTurma::new)
                .collect(Collectors.toList());
    }
}
