package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.aluno.DadosAtualizaAluno;
import com.cefet.rj.mg.sisca.domain.curso.Curso;
import com.cefet.rj.mg.sisca.domain.curso.CursoRepository;
import com.cefet.rj.mg.sisca.domain.cursoGrade.*;
import com.cefet.rj.mg.sisca.domain.materia.Materia;
import com.cefet.rj.mg.sisca.domain.materia.MateriaRepository;
import com.cefet.rj.mg.sisca.infra.security.exception.CursoGradeNotFoundException;
import com.cefet.rj.mg.sisca.infra.security.exception.CursoNotFoundException;
import com.cefet.rj.mg.sisca.infra.security.exception.MateriaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class    CursoGradeService {

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

        CursoGrade cursoGrade = new CursoGrade(dados, curso, materia);
        return cursoGradeRepository.save(cursoGrade);
    }

    public CursoGrade cursoGradeAtualizar(DadosAtualizaCursoGrade dados){

        CursoGradeId cursoGradeId = new CursoGradeId(dados.id_curso(), dados.id_materia());

        CursoGrade cursoGrade = cursoGradeRepository.findById(cursoGradeId)
                .orElseThrow(() -> new CursoGradeNotFoundException("Esse curso não esta presente na grade"));

        cursoGrade.atualizaPeriodo(dados.periodo());

        cursoGradeRepository.save(cursoGrade);

        return cursoGrade;
    }

    public void deletarCursoGrade(Long idCurso, Long idMateria) {
        CursoGradeId cursoGradeId = new CursoGradeId(idCurso, idMateria);
        CursoGrade cursoGrade = cursoGradeRepository.findById(cursoGradeId)
                .orElseThrow(() -> new CursoGradeNotFoundException("Esse curso não está presente na grade"));
        cursoGradeRepository.delete(cursoGrade);
    }
}
