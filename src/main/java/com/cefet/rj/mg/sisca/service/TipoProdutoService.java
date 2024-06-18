package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.almoxarifado.TipoProduto;
import com.cefet.rj.mg.sisca.domain.almoxarifado.TipoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoProdutoService {

    @Autowired
    TipoProdutoRepository tipoProdutoRepository;

    public TipoProduto salvarProduto(TipoProduto tipoProduto) {
        tipoProdutoRepository.save(tipoProduto);
        return tipoProduto;
    }

    public List<TipoProduto> listarTipoProduto() {
        return tipoProdutoRepository.findAll();
    }
}
