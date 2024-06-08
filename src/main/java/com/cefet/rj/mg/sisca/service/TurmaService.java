package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.funcionario.Funcionario;
import com.cefet.rj.mg.sisca.domain.turma.Turma;
import com.cefet.rj.mg.sisca.domain.turma.TurmaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TurmaService {

    TurmaRepository turmaRepository;

    public Optional<Turma> encontrarTurma(Long id_turma) {
        return turmaRepository.findById(id_turma);
    }

    public Turma salvarTurma(Turma novaTurma) {
        FuncionarioService funcionarioService = new FuncionarioService();
        Optional<Funcionario> funcionarioProvisorio = funcionarioService.encontrarfuncionario(novaTurma.getId_funcionario());
        if(funcionarioProvisorio.isPresent()){
            Funcionario funcionario = funcionarioProvisorio.get();
            if (!funcionario.isProfessor(funcionario.getId_funcionario())) {
                throw new IllegalArgumentException("Somente funcionários com a role de PROFESSOR podem ser usados para cadastro de uma turma.");
            }
        }else {
            throw new IllegalArgumentException("Funcionário com id " + novaTurma.getId_funcionario() + " não encontrado.");
        }

        return turmaRepository.save(novaTurma);
    }
}
