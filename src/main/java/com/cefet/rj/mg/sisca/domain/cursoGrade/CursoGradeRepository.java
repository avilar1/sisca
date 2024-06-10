package com.cefet.rj.mg.sisca.domain.cursoGrade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoGradeRepository extends JpaRepository<CursoGrade, Long> {

}
