package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.alunoTurma.AlunoTurma;
import com.cefet.rj.mg.sisca.domain.alunoTurma.DadosDetalhamentoAlunoTurma;
import com.cefet.rj.mg.sisca.domain.alunoTurma.StatusAlunoTurma;
import com.cefet.rj.mg.sisca.domain.alunoTurma.TurmaAlunoId;
import com.cefet.rj.mg.sisca.domain.turma.Turma;
import com.cefet.rj.mg.sisca.domain.turmaAlunoFrequencia.TurmaAlunoFrequencia;
import com.cefet.rj.mg.sisca.domain.turmaAlunoFrequencia.TurmaAlunoFrequenciaRepository;
import com.cefet.rj.mg.sisca.domain.turmaAlunoNota.TurmaAlunoNota;
import com.cefet.rj.mg.sisca.domain.turmaAlunoNota.TurmaAlunoNotaRepository;
import com.cefet.rj.mg.sisca.infra.security.exception.CursoNotFoundException;
import com.cefet.rj.mg.sisca.service.AlunoService;
import com.cefet.rj.mg.sisca.service.AlunoTurmaService;
import com.cefet.rj.mg.sisca.service.TurmaAlunoNotaService;
import com.cefet.rj.mg.sisca.service.TurmaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/situacaoAlunoTurma")
public class AlunoTurmaController {
    @Autowired
    private AlunoTurmaService alunoTurmaService;

    @Autowired
    TurmaService turmaService;

    @Autowired
    AlunoService alunoService;

    @Autowired
    TurmaAlunoFrequenciaRepository turmaAlunoFrequenciaRepository;

    @Autowired
    TurmaAlunoNotaRepository turmaAlunoNotaRepository;
    @Autowired
    private TurmaAlunoNotaService turmaAlunoNotaService;

    @GetMapping("/{idTurma}")
    public ResponseEntity pegarUmaTurma(@PathVariable Long idTurma) {
        List<DadosDetalhamentoAlunoTurma> alunoTurma = alunoTurmaService.buscarAlunosPorTurma(idTurma);

        return ResponseEntity.ok(alunoTurma);
    }

    @PostMapping("/cadastrarEmTurma")
    @Transactional
    public ResponseEntity cadastrarEmTurma(@RequestParam Long id_turma, @RequestParam Long id_aluno) {
        Turma turma = turmaService.pegarUmaTurma(id_turma);
        Aluno aluno = alunoService.pegarUmAluno(id_aluno);

        try {
            AlunoTurma alunoTurma = new AlunoTurma(turma, aluno, StatusAlunoTurma.CURSANDO);
            alunoTurmaService.salvarAlunoTurma(alunoTurma);

            TurmaAlunoNota turmaAlunoNota = new TurmaAlunoNota(aluno, turma);
            turmaAlunoNotaService.salvarAlunoENota(turmaAlunoNota);

            return ResponseEntity.ok(new DadosDetalhamentoAlunoTurma(alunoTurma));
        } catch (CursoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/trancarMatricula")
    public ResponseEntity<AlunoTurma> trancarMatricula(@RequestParam Long id_turma, @RequestParam Long id_aluno) {
        AlunoTurma alunoTurma = alunoTurmaService.buscarAlunoPorTurma(id_aluno, id_turma);
        alunoTurmaService.trancarMatricula(alunoTurma);

        return ResponseEntity.ok(alunoTurma);
    }

    @DeleteMapping("/deletar")
    public ResponseEntity deletarAlunoTurma(@RequestParam Long id_turma, @RequestParam Long id_aluno) {
        AlunoTurma alunoTurma = alunoTurmaService.buscarAlunoPorTurma(id_aluno, id_turma);
        alunoTurmaService.deletarAlunoTurma(alunoTurma);

        return ResponseEntity.ok(alunoTurma);
    }

    @PostMapping("/cadastrarFalta")
    public ResponseEntity cadastrarFalta(@RequestParam Long id_aluno, @RequestParam Long id_turma, @RequestParam Date faltou){
        var turmaOptional = turmaService.encontrarTurma(id_turma);
        var alunoOptional = alunoService.detalharaluno(id_aluno) != null ? alunoService.detalharaluno(id_aluno) : null;

        if (turmaOptional.isPresent() && alunoOptional != null) {

            var turma = turmaOptional.get();
            var aluno = alunoOptional;
            try {
                TurmaAlunoFrequencia alunoTurmaFrequencia = new TurmaAlunoFrequencia(aluno, turma, faltou);

                turmaAlunoFrequenciaRepository.save(alunoTurmaFrequencia);
                return ResponseEntity.ok("falta do dia "+ faltou +" cadastrada");
            } catch (CursoNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("turma ou aluno não encontrados");
        }
    }

    @PostMapping("/cadastrarNota")
    public ResponseEntity cadastrarNota(@RequestParam Long id_aluno, @RequestParam Long id_turma, @RequestParam Float p1, @RequestParam Float p2, @RequestParam Float pf){
        var turmaOptional = turmaService.encontrarTurma(id_turma);
        var alunoOptional = alunoService.detalharaluno(id_aluno) != null ? alunoService.detalharaluno(id_aluno) : null;

        String Qprova;
        Object prova;
        prova = (Qprova = (p1 != null ? "p1" : p2 != null ? "p2" : "pf")).equals("p1") ? p1 :
                Qprova.equals("p2") ? p2 : pf;

        if (turmaOptional.isPresent() && alunoOptional != null) {

            var turma = turmaOptional.get();
            var aluno = alunoOptional;
            try {
                TurmaAlunoNota turmaAlunoNota = new TurmaAlunoNota();

                turmaAlunoNotaRepository.save(turmaAlunoNota);
                return ResponseEntity.ok("nota da prova "+ Qprova +" cadastrada");
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
