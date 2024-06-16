package com.cefet.rj.mg.sisca.domain.financeiro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoletoRepository extends JpaRepository<Boleto, String> {
    List<Boleto> findByIdUsuario(Long idUsuario);
}
