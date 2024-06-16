package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.biblioteca.*;
import com.cefet.rj.mg.sisca.domain.usuario.Usuario;
import com.cefet.rj.mg.sisca.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Emprestimo save(DadosCadastroEmprestimo dados) {
        Livro livro = livroRepository.findById(dados.idLivro()).orElseThrow();
        Usuario usuario = usuarioRepository.findById(dados.idUsuario()).orElseThrow();
        return emprestimoRepository.save(new Emprestimo(dados, livro, usuario));
    }

    public List<Emprestimo> findAll() {
        return emprestimoRepository.findAll();
    }
}
