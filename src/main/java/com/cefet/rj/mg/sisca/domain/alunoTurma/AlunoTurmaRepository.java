package com.cefet.rj.mg.sisca.domain.alunoTurma;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AlunoTurmaRepository extends JpaRepository<AlunoTurma, TurmaAlunoId> {

    @Query("SELECT at FROM AlunoTurma at WHERE at.id.idTurma = :idTurma")
    List<AlunoTurma> findByIdTurma(@Param("idTurma") Long idTurma);

    @Query("SELECT at FROM AlunoTurma at WHERE at.id.idAluno = :idAluno")
    List<AlunoTurma> findByIdAluno(@Param("idAluno") Long idAluno);
}
