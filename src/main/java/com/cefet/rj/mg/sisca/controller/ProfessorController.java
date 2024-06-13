package com.cefet.rj.mg.sisca.controller;


import com.cefet.rj.mg.sisca.domain.funcionario.DadosDetalhamentoFuncionario;
import com.cefet.rj.mg.sisca.domain.professor.DadosCadastroProfessor;
import com.cefet.rj.mg.sisca.domain.professor.Professor;
import com.cefet.rj.mg.sisca.domain.professor.ProfessorRepository;
import com.cefet.rj.mg.sisca.domain.professor.ProfessorService;
import com.cefet.rj.mg.sisca.infra.security.exception.FuncionarioRoleWrongException;
import com.cefet.rj.mg.sisca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("professor")
public class ProfessorController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProfessorService professorService;
    @Autowired
    private ProfessorRepository professorRepository;


    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarProfessor(@RequestBody DadosCadastroProfessor dados){
        var usuarioOptional = usuarioService.encontrarUsuario(dados.id_usuario());



        if (usuarioOptional.isPresent()) {
            var usuario = usuarioOptional.get();

            try {
                Professor professor = new Professor(usuario, dados.matricula_funcionario());

                Professor professorSave = professorService.salvarProfessor(professor);

                return ResponseEntity.ok(new DadosDetalhamentoFuncionario(professorSave));
            } catch (FuncionarioRoleWrongException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com id " + dados.id_usuario() + " não encontrado");
        }
    }

    @GetMapping("/listarTodos")
    public ResponseEntity listarTodosProfessores() {
        List<Professor> professores = professorService.listarTodosProfessores();

        List<DadosDetalhamentoFuncionario> detalhesProfessores = professores.stream()
                .map(DadosDetalhamentoFuncionario::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(detalhesProfessores);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarTodosProfessores(@PathVariable Long id) {
        Professor professor = professorService.listarProfessor(id);

        return ResponseEntity.ok(new DadosDetalhamentoFuncionario(professor));
    }

    @DeleteMapping("/deletar")
    @Transactional
    public ResponseEntity deletarProfessor(@RequestParam Long idProfessor) {
        Professor professor = professorService.listarProfessor(idProfessor);

        professorRepository.delete(professor);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
