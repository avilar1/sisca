package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.curso.Curso;
import com.cefet.rj.mg.sisca.domain.curso.CursoRepository;
import com.cefet.rj.mg.sisca.domain.cursoGrade.CursoGrade;
import com.cefet.rj.mg.sisca.domain.cursoGrade.CursoGradeRepository;
import com.cefet.rj.mg.sisca.domain.cursoGrade.DadosCadastroCursoGrade;
import com.cefet.rj.mg.sisca.domain.materia.Materia;
import com.cefet.rj.mg.sisca.domain.materia.MateriaRepository;
import com.cefet.rj.mg.sisca.infra.security.exception.CursoNotFoundException;
import com.cefet.rj.mg.sisca.infra.security.exception.MateriaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoGradeService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private MateriaRepository materiaRepository;
    @Autowired
    private CursoGradeRepository cursoGradeRepository;

    public CursoGrade cadastraMateriaNoCurso(DadosCadastroCursoGrade dados) {
        Curso curso = cursoRepository.findById(dados.id_curso())
                .orElseThrow(() -> new CursoNotFoundException("Curso com id " + dados.id_curso() + " não encontrado"));

        Materia materia = materiaRepository.findById(dados.id_materia())
                .orElseThrow(() -> new MateriaNotFoundException("Matéria com id " + dados.id_materia() + " não encontrada"));

        CursoGrade cursoGrade = new CursoGrade(dados);
        return cursoGradeRepository.save(cursoGrade);
    }
}
