package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.domain.almoxarifado.DadoCadastroTipoProduto;
import com.cefet.rj.mg.sisca.domain.almoxarifado.TipoProduto;
import com.cefet.rj.mg.sisca.service.TipoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoProduto")
public class TipoProdutoController {

    @Autowired
    TipoProdutoService tipoProdutoService;

    @PostMapping
    public ResponseEntity cadastrarTipoProduto(@RequestBody DadoCadastroTipoProduto dado) {

        TipoProduto tipoProduto = new TipoProduto(dado.nome());

        tipoProdutoService.salvarProduto(tipoProduto);

        return ResponseEntity.ok(tipoProduto);
    }

    @GetMapping
    public List<TipoProduto> listarTipoProduto() {
        return tipoProdutoService.listarTipoProduto();
    }

}
