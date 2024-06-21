package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.domain.almoxarifado.Almoxarifado;
import com.cefet.rj.mg.sisca.domain.almoxarifado.DadosCadastroAlmoxarifado;
import com.cefet.rj.mg.sisca.domain.funcionario.DadosDetalhamentoFuncionario;
import com.cefet.rj.mg.sisca.domain.professor.Professor;
import com.cefet.rj.mg.sisca.infra.security.exception.FuncionarioRoleWrongException;
import com.cefet.rj.mg.sisca.service.AlmoxarifadoService;
import com.cefet.rj.mg.sisca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("almoxarifado")
public class AlmoxarifadoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AlmoxarifadoService almoxarifadoService;

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarAlmoxarifado(@RequestBody DadosCadastroAlmoxarifado dados){
        var usuarioOptional = usuarioService.encontrarUsuario(dados.id_usuario());

        if (usuarioOptional.isPresent()) {
            var usuario = usuarioOptional.get();

            try {
                Almoxarifado almoxarifado = new Almoxarifado(usuario, dados.matricula_funcionario());

                Almoxarifado almoxarifadoSave = almoxarifadoService.salvarAlmoxarifado(almoxarifado);

                return ResponseEntity.ok(new DadosDetalhamentoFuncionario(almoxarifadoSave));
            } catch (FuncionarioRoleWrongException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com id " + dados.id_usuario() + " não encontrado");
        }
    }

    @GetMapping()
    public ResponseEntity buscarAlmoxarifados() {
        List<DadosDetalhamentoFuncionario> almoxarifado = almoxarifadoService.buscarTodos();

        return ResponseEntity.ok(almoxarifado);
    }

}
