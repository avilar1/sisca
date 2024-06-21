package com.cefet.rj.mg.sisca.domain.funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query("SELECT f FROM Funcionario f WHERE f.usuario.id_usuario = :id_usuario")
    Optional<Funcionario> findByUsuario(@Param("id_usuario") Long id_usuario);
}
