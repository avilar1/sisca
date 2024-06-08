package com.cefet.rj.mg.sisca.domain.interfaces;

import com.cefet.rj.mg.sisca.domain.curso.Curso;
import com.cefet.rj.mg.sisca.infra.security.exception.CursoNotFoundException;
import com.cefet.rj.mg.sisca.service.CursoService;

public interface CursoInterface {
    public default Curso retornarCurso(Long id_curso) {
        CursoService cursoService = new CursoService();
        return cursoService.buscarCursoPorId(id_curso)
                .orElseThrow(() -> new CursoNotFoundException("Curso com id " + id_curso + " não encontrado"));
    }
}
