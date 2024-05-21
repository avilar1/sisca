package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.domain.aluno.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<DadosListagemAluno>> listarTodos(){

        List<DadosListagemAluno> alunos = alunoService.listarTodos();
        return ResponseEntity.ok(alunos);

    }

//    public ResponseEntity listar(){
//        var alunos = alunoRepository.findAll();
//        return ResponseEntity.ok(alunos);
//    }

    @GetMapping("/{id}")
    public ResponseEntity detalharAluno(@PathVariable Long id) {
            Aluno aluno = alunoService.detalharaluno(id);
        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }


}
