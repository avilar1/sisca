package com.cefet.rj.mg.sisca.domain.almoxarifado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    @Query("SELECT e FROM Estoque e WHERE e.produto.id_produto = :idProduto")
    List<Estoque> findByProduto(@Param("idProduto") Long idProduto);
}
