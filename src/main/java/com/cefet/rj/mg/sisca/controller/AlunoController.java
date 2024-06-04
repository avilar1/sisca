package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.domain.aluno.*;
import com.cefet.rj.mg.sisca.service.AlunoService;
import com.cefet.rj.mg.sisca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AlunoService alunoService;

    private UsuarioService usuarioService;

    @GetMapping("/teste")
    public String teste(){
        return "Wololo";
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemAluno>> listarTodos(){

        List<DadosListagemAluno> alunos = alunoService.listarTodos();
        return ResponseEntity.ok(alunos);

    }

    @PostMapping
    public ResponseEntity cadastrarAluno(@RequestBody DadosCadastroAluno dadosCadastroAluno,@RequestParam Long idUsuario) {
        var usuarioOptional = usuarioService.encontrarUsuario(idUsuario);

        if (usuarioOptional.isPresent()) {
            var usuario = usuarioOptional.get();


            Aluno novoAluno = new Aluno(dadosCadastroAluno, usuario);

            alunoService.salvarAluno(novoAluno);
            return ResponseEntity.ok(new DadosDetalhamentoAluno(novoAluno));
        } else {
            return ResponseEntity.notFound().build();

        }
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
