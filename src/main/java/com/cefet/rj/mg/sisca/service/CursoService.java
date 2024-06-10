package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.curso.DadosDetalhamentoCurso;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.cefet.rj.mg.sisca.domain.curso.CursoRepository;
import com.cefet.rj.mg.sisca.domain.curso.Curso;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoService  {

    @Autowired
    private CursoRepository cursoRepository;


    public List<DadosDetalhamentoCurso> listarTodos() {
        return cursoRepository.findAll().stream()
                .map(DadosDetalhamentoCurso::new)
                .collect(Collectors.toList());
    }

    public List<Curso> buscarCursosPorIds(List<Long> ids) {
        return cursoRepository.findAllById(ids);
    }

    public Curso buscarCursoPorId(Long id_curso) {
        return cursoRepository.findById(id_curso).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    }

}
