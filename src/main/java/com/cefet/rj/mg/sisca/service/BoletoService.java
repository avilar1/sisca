package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.financeiro.Boleto;
import com.cefet.rj.mg.sisca.domain.financeiro.BoletoRepository;
import com.cefet.rj.mg.sisca.domain.financeiro.DadosCadastroBoleto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoletoService {
    @Autowired
    private BoletoRepository boletoRepository;

    public Boleto save(DadosCadastroBoleto dados) {
        return boletoRepository.save(new Boleto(dados));
    }

    public List<Boleto> findAll() {
        return boletoRepository.findAll();
    }

    public Boleto findById(String codigoDeBarras) {
        return boletoRepository.findById(codigoDeBarras).orElseThrow();
    }

    public List<Boleto> findByUsuario(Long idUsuario) {
        return boletoRepository.findByIdUsuario(idUsuario);
    }
}
