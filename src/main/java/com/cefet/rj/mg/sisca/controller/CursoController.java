package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.domain.curso.Curso;
import com.cefet.rj.mg.sisca.domain.curso.CursoRepository;
import com.cefet.rj.mg.sisca.domain.curso.DadosCadastroCurso;
import com.cefet.rj.mg.sisca.domain.curso.DadosDetalhamentoCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;
    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarCurso(@RequestBody DadosCadastroCurso dadosCadastroCurso){
        var curso = cursoRepository.save(new Curso(dadosCadastroCurso));
        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
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


}
