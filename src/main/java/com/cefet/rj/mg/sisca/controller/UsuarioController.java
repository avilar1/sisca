package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.domain.usuario.*;
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

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody DadosLoginUsuario dados) {
        Usuario usuario = usuarioService.login(dados);

        return ResponseEntity.ok(usuario);
    }

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

    @GetMapping("/{id}")
    public ResponseEntity detalharUsuario(@PathVariable Long id) {
        var usuarioOptional = usuarioService.encontrarUsuario(id);

        if (usuarioOptional.isPresent()) {
            var usuario = usuarioOptional.get();
            return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity atualizaUsuario(@RequestBody DadosAtualizaUsuario dados){

        var usuario = usuarioService.atualizarUsuario(dados);

        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }
}
