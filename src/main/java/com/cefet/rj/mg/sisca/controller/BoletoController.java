package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.domain.financeiro.Boleto;
import com.cefet.rj.mg.sisca.domain.financeiro.DadosCadastroBoleto;
import com.cefet.rj.mg.sisca.service.BoletoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boletos")
public class BoletoController {

    @Autowired
    private BoletoService boletoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Boleto> createBoleto(@RequestBody DadosCadastroBoleto dados) {
        Boleto boleto = boletoService.save(dados);
        return ResponseEntity.ok(boleto);
    }

    @GetMapping
    public List<Boleto> getAllBoletos() {
        return boletoService.findAll();
    }

    @GetMapping("/{codigoDeBarras}")
    public ResponseEntity<Boleto> getBoletoById(@PathVariable String codigoDeBarras) {
        Boleto boleto = boletoService.findById(codigoDeBarras);
        return ResponseEntity.ok(boleto);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Boleto> getBoletosByUsuario(@PathVariable Long idUsuario) {
        return boletoService.findByUsuario(idUsuario);
    }
}
