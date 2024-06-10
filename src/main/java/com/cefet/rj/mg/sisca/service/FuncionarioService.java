package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.funcionario.Funcionario;
import com.cefet.rj.mg.sisca.domain.funcionario.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioService {
    FuncionarioRepository funcionarioRepository;

    public Optional<Funcionario> encontrarfuncionario(Long id_funcionario) {
        return funcionarioRepository.findById(id_funcionario);
    }
}
