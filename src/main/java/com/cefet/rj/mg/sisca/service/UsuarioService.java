package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.usuario.DadosAtualizaUsuario;
import com.cefet.rj.mg.sisca.domain.usuario.DadosListagemUsuario;
import com.cefet.rj.mg.sisca.domain.usuario.Usuario;
import com.cefet.rj.mg.sisca.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<DadosListagemUsuario> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(DadosListagemUsuario::new)
                .collect(Collectors.toList());
    }

    public Optional<Usuario> usuarioFindById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario atualizarUsuario(DadosAtualizaUsuario dados){

        Usuario usuarioExistente = usuarioRepository.findById(dados.id_usuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioExistente.atualizarUsuario(dados);

        usuarioRepository.save(usuarioExistente);

        return usuarioExistente;
    }

    public Optional<Usuario> encontrarUsuario(Long id) {
        var usuario = usuarioRepository.findById(id);
        if(usuario == null){
            throw new RuntimeException();
        }
        return usuario;
    }
}
