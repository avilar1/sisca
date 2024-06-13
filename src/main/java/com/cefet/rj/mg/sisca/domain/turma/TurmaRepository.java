package com.cefet.rj.mg.sisca.domain.turma;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

    @Query("SELECT t FROM Turma t WHERE t.semestre_turma = :semestre")
    List<Turma> findAllBySemestreTurma(@Param("semestre") String semestre);
}
