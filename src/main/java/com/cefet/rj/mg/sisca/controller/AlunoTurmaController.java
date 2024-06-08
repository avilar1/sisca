package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.domain.alunoTurma.AlunoTurma;
import com.cefet.rj.mg.sisca.domain.alunoTurma.DadosDetalhamentoAlunoTurma;
import com.cefet.rj.mg.sisca.infra.security.exception.CursoNotFoundException;
import com.cefet.rj.mg.sisca.service.AlunoService;
import com.cefet.rj.mg.sisca.service.AlunoTurmaService;
import com.cefet.rj.mg.sisca.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/situacaoAlunoTurma")
public class AlunoTurmaController {
    @Autowired
    private AlunoTurmaService alunoTurmaService;

    @Autowired
    TurmaService turmaService;

    @Autowired
    AlunoService alunoService;

    @PostMapping
    public ResponseEntity cadastrarEmTurma(@RequestParam Long id_turma, @RequestParam Long id_aluno) {
    var turmaOptional = turmaService.encontrarTurma(id_turma);
    var alunoOptional = alunoService.detalharaluno(id_aluno) != null ? alunoService.detalharaluno(id_aluno) : null;

        if (turmaOptional.isPresent() && alunoOptional != null) {

//            var turma = turmaOptional.get();
//            var aluno = alunoOptional;

            try {
                AlunoTurma alunoTurma = new AlunoTurma(id_turma, id_aluno);

                alunoTurmaService.salvarAlunoTurma(alunoTurma);
                return ResponseEntity.ok(new DadosDetalhamentoAlunoTurma(alunoTurma));
            } catch (CursoNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("turma ou aluno não encontrados");
        }
    }





















//    @GetMapping
//    public ResponseEntity<DadosSituacaoAlunoTurma> obterSituacao(@RequestParam Long alunoId, @RequestParam Long turmaId) {
//        DadosSituacaoAlunoTurma situacao = alunoTurmaService.obterSituacaoAlunoTurma(alunoId, turmaId);
//        return ResponseEntity.ok(situacao);
//    }
//
//    @GetMapping
//    public ResponseEntity<DadosNotaAlunoTurma> obterNota(@RequestParam Long alunoId, @RequestParam Long turmaId) {
//        DadosNotaAlunoTurma nota = alunoTurmaService.obterNotaAlunoTurma(alunoId, turmaId);
//        return ResponseEntity.ok(nota);
//    }
//
//    @GetMapping
//    public ResponseEntity<DadosFrequenciaAlunoTurma> obterFrequencia(@RequestParam Long alunoId, @RequestParam Long turmaId) {
//        DadosFrequenciaAlunoTurma frequencia = alunoTurmaService.obterFrequenciaAlunoTurma(alunoId, turmaId);
//        return ResponseEntity.ok(frequencia);
//    }
}
