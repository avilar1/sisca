package com.cefet.rj.mg.sisca.controller;


import com.cefet.rj.mg.sisca.domain.biblioteca.DadosAtualizacaoLivro;
import com.cefet.rj.mg.sisca.domain.biblioteca.DadosCadastroLivro;
import com.cefet.rj.mg.sisca.domain.biblioteca.DadosDetalhamentoLivro;
import com.cefet.rj.mg.sisca.domain.biblioteca.Livro;
import com.cefet.rj.mg.sisca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroService livroService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Livro> createLivro(@RequestBody DadosCadastroLivro dados) {
        Livro livro = livroService.save(dados);
        return ResponseEntity.ok(livro);
    }

    @GetMapping
    public List<Livro> getAllLivros() {
        return livroService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoLivro> detalharLivro(@PathVariable Long id) {
        DadosDetalhamentoLivro detalhes = livroService.detalhar(id);
        return ResponseEntity.ok(detalhes);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Livro> atualizarLivro(@RequestBody DadosAtualizacaoLivro dados) {
        Livro livro = livroService.atualizar(dados);
        return ResponseEntity.ok(livro);
    }
}
