package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.funcionario.Funcionario;
import com.cefet.rj.mg.sisca.domain.funcionario.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Optional<Funcionario> encontrarfuncionario(Long id_funcionario) {
        return funcionarioRepository.findById(id_funcionario);
    }

    public Funcionario encontrarFuncionarioPorUsuario(Long id_usuario) {
        return funcionarioRepository.findByUsuario(id_usuario)
                .orElseThrow(() -> new RuntimeException("Funcionario não existe"));
    }

}
