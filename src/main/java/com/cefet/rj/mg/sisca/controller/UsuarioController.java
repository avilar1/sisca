package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.domain.aluno.DadosListagemAluno;
import com.cefet.rj.mg.sisca.domain.usuario.DadosCadastroUsuario;
import com.cefet.rj.mg.sisca.domain.usuario.DadosListagemUsuario;
import com.cefet.rj.mg.sisca.domain.usuario.DadosAtualizaUsuario;
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

    @PutMapping("/atualizar")
    @Transactional
    public Usuario atualizaUsuario(@RequestBody DadosAtualizaUsuario dados){

        Usuario usuarioExistente = usuarioRepository.findById(dados.id_usuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (dados.nome() != null) {
            usuarioExistente.setNome(dados.nome());
        }

        if (dados.cpf() != null) {
            usuarioExistente.setCpf(dados.cpf());
        }

        if (dados.email() != null) {
            usuarioExistente.setEmail(dados.email());
        }

        if (dados.telefone() != null) {
            usuarioExistente.setTelefone(dados.telefone());
        }

        if (dados.data_nascimento() != null) {
            usuarioExistente.setData_nascimento(dados.data_nascimento());
        }

        if (dados.role() != null) {
            usuarioExistente.setRole(dados.role());
        }

        usuarioRepository.save(usuarioExistente);

        return usuarioExistente;
    }
}
