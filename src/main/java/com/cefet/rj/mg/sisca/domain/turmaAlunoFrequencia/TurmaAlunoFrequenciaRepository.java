package com.cefet.rj.mg.sisca.domain.turmaAlunoFrequencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface TurmaAlunoFrequenciaRepository extends JpaRepository<TurmaAlunoFrequencia, TurmaAlunoFrequenciaId> {

    @Query("SELECT taf FROM TurmaAlunoFrequencia taf WHERE taf.id.idTurma = :idTurma AND taf.id.idAluno = :idAluno AND taf.faltou = :faltou")
    Optional<TurmaAlunoFrequencia> buscarTurmaPorIdEData(
            @Param("idTurma") Long idTurma,
            @Param("idAluno") Long idAluno,
            @Param("faltou") Date faltou
    );

}
