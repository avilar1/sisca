package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.almoxarifado.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private TipoProdutoRepository tipoProdutoRepository;

    public Produto save(DadosCadastroProduto dados) {
        TipoProduto tipoProduto = tipoProdutoRepository.findById(dados.tipoProduto().getId_tipo_produto()).orElseThrow();
        return produtoRepository.save(new Produto(dados, tipoProduto));
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(Long id) {
        return produtoRepository.findById(id).orElseThrow();
    }

}
