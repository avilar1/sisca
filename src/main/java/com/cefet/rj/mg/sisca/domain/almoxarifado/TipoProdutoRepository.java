package com.cefet.rj.mg.sisca.domain.almoxarifado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface TipoProdutoRepository extends JpaRepository<TipoProduto, Long> {
}
