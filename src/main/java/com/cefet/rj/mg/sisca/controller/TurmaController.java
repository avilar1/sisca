package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.domain.turma.DadosCadastroTurma;
import com.cefet.rj.mg.sisca.domain.turma.DadosDetalhamentoTurma;
import com.cefet.rj.mg.sisca.domain.turma.Turma;
import com.cefet.rj.mg.sisca.infra.security.exception.CursoNotFoundException;
import com.cefet.rj.mg.sisca.service.FuncionarioService;
import com.cefet.rj.mg.sisca.service.MateriaService;
import com.cefet.rj.mg.sisca.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TurmaController {

    @Autowired
    FuncionarioService funcionarioService;
    @Autowired
    MateriaService materiaService;
    @Autowired
    TurmaService turmaService;

    @PostMapping
    public ResponseEntity cadastrarTurma(@RequestBody DadosCadastroTurma dadosCadastroTurma) {
        var funcionarioOptional = funcionarioService.encontrarfuncionario(dadosCadastroTurma.id_funcionario());

        var materiaOptional = materiaService.buscarMateriaPorId(dadosCadastroTurma.id_materia());

        if (funcionarioOptional.isPresent() && materiaOptional.isPresent()) {

            var funcionario = funcionarioOptional.get();
            var materia = materiaOptional.get();

            try {
                Turma novaTurma = new Turma(dadosCadastroTurma);

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
}
