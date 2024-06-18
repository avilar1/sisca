package com.cefet.rj.mg.sisca.domain.aluno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {


    @Query("SELECT a FROM Aluno a WHERE a.usuario.id_usuario = id")
    Optional<Aluno> pegarAlunoPorUsuario(
            @Param("id") Long id
    );
}