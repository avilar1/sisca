package com.cefet.rj.mg.sisca.domain.aluno;

import com.cefet.rj.mg.sisca.domain.nota.Nota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public List<DadosListagemAluno> listarTodos() {
        return alunoRepository.findAllByAtivoTrue().stream()
                .map(DadosListagemAluno::new)
                .collect(Collectors.toList());
    }

    public Aluno detalharaluno(Long id) {
        return alunoRepository.getReferenceById(id);
    }

    public void RegistroDeSugestoesEReclamacoes() {
        return;
    }

}