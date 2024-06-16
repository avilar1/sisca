package com.cefet.rj.mg.sisca.controller;


import com.cefet.rj.mg.sisca.domain.biblioteca.DadosCadastroEmprestimo;
import com.cefet.rj.mg.sisca.domain.biblioteca.Emprestimo;
import com.cefet.rj.mg.sisca.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoController {
    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Emprestimo> createEmprestimo(@RequestBody DadosCadastroEmprestimo dados) {
        Emprestimo emprestimo = emprestimoService.save(dados);
        return ResponseEntity.ok(emprestimo);
    }

    @GetMapping
    public List<Emprestimo> getAllEmprestimos() {
        return emprestimoService.findAll();
    }
}
