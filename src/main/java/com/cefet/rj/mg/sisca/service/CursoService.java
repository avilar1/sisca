package com.cefet.rj.mg.sisca.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.cefet.rj.mg.sisca.domain.curso.CursoRepository;
import com.cefet.rj.mg.sisca.domain.curso.Curso;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService  {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> buscarCursosPorIds(List<Long> ids) {
        return cursoRepository.findAllById(ids);
    }

    public Optional<Curso> buscarCursoPorId(Long id_curso) { return cursoRepository.findById(id_curso);}

}
