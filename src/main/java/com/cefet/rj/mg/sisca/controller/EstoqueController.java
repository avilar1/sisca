package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.domain.almoxarifado.DadosCadastroEstoque;
import com.cefet.rj.mg.sisca.domain.almoxarifado.Estoque;
import com.cefet.rj.mg.sisca.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Estoque> createEstoque(@RequestBody DadosCadastroEstoque dados) {
        Estoque estoque = estoqueService.save(dados);
        return ResponseEntity.ok(estoque);
    }

    @GetMapping
    public List<Estoque> getAllEstoques() {
        return estoqueService.findAll();
    }

    @GetMapping("/produto/{id_produto}")
    public List<Estoque> getEstoqueByProduto(@PathVariable Long idProduto) {
        return estoqueService.findByProduto(idProduto);
    }

}
