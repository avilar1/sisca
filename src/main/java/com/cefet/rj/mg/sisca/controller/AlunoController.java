package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.domain.aluno.*;
import com.cefet.rj.mg.sisca.domain.alunoCurso.AlunoCurso;
import com.cefet.rj.mg.sisca.domain.alunoCurso.AlunoCursoRepository;
import com.cefet.rj.mg.sisca.domain.alunoCurso.DadosCadastroAlunoCurso;
import com.cefet.rj.mg.sisca.domain.curso.Curso;
import com.cefet.rj.mg.sisca.infra.security.exception.CursoNotFoundException;
import com.cefet.rj.mg.sisca.service.AlunoService;
import com.cefet.rj.mg.sisca.service.CursoService;
import com.cefet.rj.mg.sisca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {


    @Autowired
    private AlunoService alunoService;

    @Autowired
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

            try {
                Aluno novoAluno = new Aluno(dadosCadastroAluno, usuario);
                alunoService.salvarAluno(novoAluno);

                return ResponseEntity.ok(new DadosDetalhamentoAluno(novoAluno));
            } catch (CursoNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com id " + idUsuario + " não encontrado");
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

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity atualizaAluno(@RequestBody DadosAtualizaAluno dados){
        try {
            var aluno = alunoService.atualizarAluno(dados);
            return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
        } catch (CursoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
