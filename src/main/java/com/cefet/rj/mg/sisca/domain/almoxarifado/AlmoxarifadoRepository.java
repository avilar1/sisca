package com.cefet.rj.mg.sisca.domain.almoxarifado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlmoxarifadoRepository extends JpaRepository<Almoxarifado, Long> {
}
