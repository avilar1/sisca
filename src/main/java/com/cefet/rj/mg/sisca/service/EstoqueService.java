package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.almoxarifado.*;
import com.cefet.rj.mg.sisca.domain.funcionario.Funcionario;
import com.cefet.rj.mg.sisca.domain.funcionario.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Estoque save(DadosCadastroEstoque dados) {
        Produto produto = produtoRepository.findById(dados.idProduto()).orElseThrow();
        Funcionario funcionario = funcionarioRepository.findById(dados.idFuncionario()).orElseThrow();
        return estoqueRepository.save(new Estoque(dados, funcionario, produto));
    }

    public List<Estoque> findAll() {
        return estoqueRepository.findAll();
    }

    public List<Estoque> findByProduto(Long idProduto) {
        return estoqueRepository.findByProduto(idProduto);
    }
}
