package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.usuario.DadosListagemUsuario;
import com.cefet.rj.mg.sisca.domain.usuario.Usuario;
import com.cefet.rj.mg.sisca.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
}
