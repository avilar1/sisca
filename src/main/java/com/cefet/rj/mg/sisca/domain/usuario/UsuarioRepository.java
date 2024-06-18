package com.cefet.rj.mg.sisca.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.cpf = :cpf AND u.senha = :senha")
    Optional<Usuario> login(
            @Param("cpf") String cpf,
            @Param("senha") String senha
    );
}
