package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.domain.funcionario.Funcionario;
import com.cefet.rj.mg.sisca.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("funcionario")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @GetMapping("/{id_usuario}")
    public ResponseEntity pegarFuncionarioPorUsuario(@PathVariable Long id_usuario) {
        Funcionario funcionario = funcionarioService.encontrarFuncionarioPorUsuario(id_usuario);

        return ResponseEntity.ok(funcionario);
    }

}
