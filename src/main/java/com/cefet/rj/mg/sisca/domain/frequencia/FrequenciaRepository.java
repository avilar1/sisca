package com.cefet.rj.mg.sisca.domain.frequencia;

import com.cefet.rj.mg.sisca.domain.nota.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FrequenciaRepository extends JpaRepository<Frequencia, Long> {
    List<Frequencia> findByAlunoTurmaId(Long alunoTurmaId);
}
