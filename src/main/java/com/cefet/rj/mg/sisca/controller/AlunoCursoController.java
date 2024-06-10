package com.cefet.rj.mg.sisca.controller;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.aluno.DadosDetalhamentoAluno;
import com.cefet.rj.mg.sisca.domain.alunoCurso.AlunoCurso;
import com.cefet.rj.mg.sisca.domain.alunoCurso.AlunoCursoRepository;
import com.cefet.rj.mg.sisca.domain.alunoCurso.DadosCadastroAlunoCurso;
import com.cefet.rj.mg.sisca.domain.curso.Curso;
import com.cefet.rj.mg.sisca.service.AlunoService;
import com.cefet.rj.mg.sisca.service.CursoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunoCurso")
public class AlunoCursoController {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private AlunoCursoRepository alunoCursoRepository;

    @Autowired
    private CursoService cursoService;

    @PostMapping("/{id_aluno}/{id_curso}")
    public ResponseEntity cadastrarAlunoCurso(@PathVariable Long id_aluno, @PathVariable Long id_curso, @RequestParam  String ano_matricula) {
        Aluno aluno = alunoService.pegarUmAluno(id_aluno);
        Curso curso = cursoService.buscarCursoPorId(id_curso);

        AlunoCurso alunoCurso = new AlunoCurso(aluno, curso, 1, ano_matricula);
        alunoCursoRepository.save(alunoCurso);

        return ResponseEntity.ok(alunoCurso);
    }
}
