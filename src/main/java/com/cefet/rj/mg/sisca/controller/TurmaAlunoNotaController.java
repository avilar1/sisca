package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.domain.aluno.DadosListagemAluno;
import com.cefet.rj.mg.sisca.domain.turmaAlunoNota.DadosAtualizaTurmaAlunoNota;
import com.cefet.rj.mg.sisca.domain.turmaAlunoNota.TurmaAlunoNota;
import com.cefet.rj.mg.sisca.domain.turmaAlunoNota.TurmaAlunoNotaId;
import com.cefet.rj.mg.sisca.service.TurmaAlunoNotaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("turmaAlunoNota")
public class TurmaAlunoNotaController {


    private final TurmaAlunoNotaService turmaAlunoNotaService;

    public TurmaAlunoNotaController(TurmaAlunoNotaService turmaAlunoNotaService) {
        this.turmaAlunoNotaService = turmaAlunoNotaService;
    }

    @PutMapping
    public ResponseEntity atualizarNotaAluno(@RequestParam Long idTurma, @RequestParam Long idAluno, @RequestBody  DadosAtualizaTurmaAlunoNota dados) {
        TurmaAlunoNotaId turmaAlunoNotaId = new TurmaAlunoNotaId(idAluno, idTurma);

        TurmaAlunoNota turmaAlunoNota = turmaAlunoNotaService.encontrarRegistro(turmaAlunoNotaId);
        turmaAlunoNota.alterarNotas(dados);

        TurmaAlunoNota updatedNota = turmaAlunoNotaService.salvarAlunoENota(turmaAlunoNota);
        return ResponseEntity.ok(updatedNota);
    }

}
