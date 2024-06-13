package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.domain.aluno.DadosListagemAluno;
import com.cefet.rj.mg.sisca.domain.materia.Materia;
import com.cefet.rj.mg.sisca.domain.professor.Professor;
import com.cefet.rj.mg.sisca.domain.professor.ProfessorService;
import com.cefet.rj.mg.sisca.domain.turma.*;
import com.cefet.rj.mg.sisca.infra.security.exception.CursoNotFoundException;
import com.cefet.rj.mg.sisca.service.MateriaService;
import com.cefet.rj.mg.sisca.service.TurmaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("turma")
public class TurmaController {

    @Autowired
    ProfessorService professorService;
    @Autowired
    MateriaService materiaService;
    @Autowired
    TurmaService turmaService;
    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping("/listarTodas")
    public ResponseEntity<List<DadosDetalhamentoTurma>> listarTodos(){

        List<DadosDetalhamentoTurma> turmas = turmaService.listarTodas();
        return ResponseEntity.ok(turmas);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarTurma(@RequestBody DadosCadastroTurma dadosCadastroTurma) {
        Professor professor = professorService.listarProfessor(dadosCadastroTurma.id_funcionario());
        var materiaOptional = materiaService.buscarMateriaPorId(dadosCadastroTurma.id_materia());

        if (materiaOptional.isPresent()) {
            Materia materia = materiaOptional.get();

            try {
                Turma novaTurma = new Turma(dadosCadastroTurma, professor, materia);

                turmaService.salvarTurma(novaTurma);
                return ResponseEntity.ok(new DadosDetalhamentoTurma(novaTurma));
            } catch (CursoNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("funcionario ou materia não encontrados");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharTurma(@PathVariable Long id_turma) {
        var turmaOptional = turmaService.encontrarTurma(id_turma);
        if (turmaOptional.isPresent()) {
            var turma = turmaOptional.get();
            return ResponseEntity.ok(new DadosDetalhamentoTurma(turma));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/periodo/{periodo}")
    public ResponseEntity pegarTurmaPeriodo(@PathVariable String periodo) {
        List<Turma> turmas = turmaService.turmaPorPeriodo(periodo);
        return ResponseEntity.ok(turmas);
    }

    @PutMapping("/atualizar")
    public ResponseEntity atualizarTurma(@RequestBody DadosAtualizaTurma dadosAtualizaTurma) {
        Turma turma = turmaService.pegarUmaTurma(dadosAtualizaTurma.id_turma());
        Materia materia = turma.getMateria();
        Professor professor = turma.getProfessor();

        if(dadosAtualizaTurma.id_materia() != null && dadosAtualizaTurma.id_materia() != turma.getMateria().getId_materia()) {
            materia = materiaService.buscarMateria(dadosAtualizaTurma.id_materia());
        }

        if(dadosAtualizaTurma.id_funcionario() != null && dadosAtualizaTurma.id_funcionario() != turma.getProfessor().getId_funcionario()) {
            professor = professorService.listarProfessor(dadosAtualizaTurma.id_funcionario());
        }

        turma.atualizar(dadosAtualizaTurma, materia, professor);
        turmaRepository.save(turma);
        return ResponseEntity.ok(new DadosDetalhamentoTurma(turma));
    }
 }
