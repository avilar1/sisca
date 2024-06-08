package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.aluno.AlunoRepository;
import com.cefet.rj.mg.sisca.domain.aluno.DadosAtualizaAluno;
import com.cefet.rj.mg.sisca.domain.aluno.DadosListagemAluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public List<DadosListagemAluno> listarTodos() {
        return alunoRepository.findAll().stream()
                .map(DadosListagemAluno::new)
                .collect(Collectors.toList());
    }

    public Aluno detalharaluno(Long id) {
        return alunoRepository.getReferenceById(id);
    }

    public void RegistroDeSugestoesEReclamacoes() {
        return;
    }

    public Aluno salvarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno atualizarAluno(DadosAtualizaAluno dados){

        Aluno alunoExistente = alunoRepository.findById(dados.id_aluno())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        alunoExistente.atualizarAluno(dados);

        alunoRepository.save(alunoExistente);

        return alunoExistente;
    }

}