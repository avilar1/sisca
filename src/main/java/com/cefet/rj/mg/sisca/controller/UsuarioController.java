package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.domain.aluno.DadosListagemAluno;
import com.cefet.rj.mg.sisca.domain.usuario.DadosCadastroUsuario;
import com.cefet.rj.mg.sisca.domain.usuario.DadosListagemUsuario;
import com.cefet.rj.mg.sisca.domain.usuario.Usuario;
import com.cefet.rj.mg.sisca.domain.usuario.UsuarioRepository;
import com.cefet.rj.mg.sisca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroUsuario dados) {
        usuarioRepository.save(new Usuario(dados));
        System.out.println("dados recebido: " +dados);
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<DadosListagemUsuario>> listarTodos(){

        List<DadosListagemUsuario> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);

    }
}
