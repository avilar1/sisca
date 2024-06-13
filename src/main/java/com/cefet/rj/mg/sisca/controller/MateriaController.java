package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.domain.materia.*;
import com.cefet.rj.mg.sisca.infra.security.exception.CursoNotFoundException;
import com.cefet.rj.mg.sisca.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("materia")
public class MateriaController {

    @Autowired
    private MateriaRepository materiaRepository;
    @Autowired
    private MateriaService materiaService;

    @GetMapping("/listarTodas")
    public ResponseEntity<List<DadosListagemMateria>> listarTodas(){

        List<DadosListagemMateria> materia = materiaService.listarTodas();
        return ResponseEntity.ok(materia);

    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarMateria(@RequestBody DadosCadastroMateria dadosCadastroMateria){

        var materia = materiaRepository.save(new Materia(dadosCadastroMateria));
        return ResponseEntity.ok(new DadosDetalhamentoMateria(materia));
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity atualizaMateria(@RequestBody DadosAtualizaMateria dados){
        try {
            var materia = materiaService.atualizarMateria(dados);
            return ResponseEntity.ok(new DadosDetalhamentoMateria(materia));
        } catch (CursoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
