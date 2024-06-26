package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.domain.curso.*;
import com.cefet.rj.mg.sisca.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CursoService cursoService;

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarCurso(@RequestBody DadosCadastroCurso dadosCadastroCurso){
        var curso = cursoRepository.save(new Curso(dadosCadastroCurso));
        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<DadosDetalhamentoCurso>> listarTodos(){

        List<DadosDetalhamentoCurso> cursos = cursoService.listarTodos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharCurso(@PathVariable Long id){
        var cursoOptional = cursoRepository.findById(id);
        if (cursoOptional.isPresent()) {
            var curso = cursoOptional.get();
            return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity atualizaCurso(@RequestBody DadosAtualizaCurso dados){

            Curso cursoExistente = cursoRepository.findById(dados.id_curso())
                    .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

            cursoExistente.atualizarCurso(dados);

            var curso = cursoRepository.save(cursoExistente);

        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
    }

}
