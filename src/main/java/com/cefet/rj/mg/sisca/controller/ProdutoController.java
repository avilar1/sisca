package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.domain.almoxarifado.DadosCadastroProduto;
import com.cefet.rj.mg.sisca.domain.almoxarifado.Produto;
import com.cefet.rj.mg.sisca.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Produto> createProduto(@RequestBody DadosCadastroProduto dados) {
        Produto produto = produtoService.save(dados);
        return ResponseEntity.ok(produto);
    }

    @GetMapping
    public List<Produto> getAllProdutos() {
        return produtoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> detalharProduto(@PathVariable Long id) {
        Produto produto = produtoService.findById(id);
        return ResponseEntity.ok(produto);
    }

}
