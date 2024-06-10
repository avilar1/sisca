package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.domain.cursoGrade.CursoGrade;
import com.cefet.rj.mg.sisca.domain.cursoGrade.DadosCadastroCursoGrade;
import com.cefet.rj.mg.sisca.service.CursoGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("cursoGrade")
public class CursoGradeController {

    @Autowired
    private CursoGradeService cursoGradeService;

    @PostMapping("/cadastrar")
    public ResponseEntity CadastrarCursoGrade(@RequestBody DadosCadastroCursoGrade dados) {
        CursoGrade cursoGrade = cursoGradeService.cadastraMateriaNoCurso(dados);
        return ResponseEntity.ok(cursoGrade);
    }

}
