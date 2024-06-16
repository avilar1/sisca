package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.biblioteca.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    public Livro save(DadosCadastroLivro dados) {
        return livroRepository.save(new Livro(dados));
    }

    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    public DadosDetalhamentoLivro detalhar(Long id) {
        Livro livro = livroRepository.findById(id).orElseThrow();
        return new DadosDetalhamentoLivro(livro.getId_livro(), livro.getTitulo(), livro.getAutor(), livro.getEditor());
    }

    public Livro atualizar(DadosAtualizacaoLivro dados) {
        Livro livro = livroRepository.findById(dados.id()).orElseThrow();
        livro.atualizarDados(dados);
        return livroRepository.save(livro);
    }
}
