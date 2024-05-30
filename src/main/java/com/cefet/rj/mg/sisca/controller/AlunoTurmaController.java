package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.service.AlunoTurmaService;
import com.cefet.rj.mg.sisca.domain.alunoTurma.DadosFrequenciaAlunoTurma;
import com.cefet.rj.mg.sisca.domain.alunoTurma.DadosNotaAlunoTurma;
import com.cefet.rj.mg.sisca.domain.alunoTurma.DadosSituacaoAlunoTurma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/situacaoAlunoTurma")
public class AlunoTurmaController {
    @Autowired
    private AlunoTurmaService alunoTurmaService;

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
