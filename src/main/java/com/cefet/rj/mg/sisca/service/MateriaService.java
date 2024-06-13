package com.cefet.rj.mg.sisca.service;

import com.cefet.rj.mg.sisca.domain.materia.DadosAtualizaMateria;
import com.cefet.rj.mg.sisca.domain.materia.DadosListagemMateria;
import com.cefet.rj.mg.sisca.domain.materia.Materia;
import com.cefet.rj.mg.sisca.domain.materia.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    public List<DadosListagemMateria> listarTodas() {
        return materiaRepository.findAll().stream()
                .map(DadosListagemMateria::new)
                .collect(Collectors.toList());
    }

    public List<Materia> buscarMateriasPorIds(List<Long> ids) {
        return materiaRepository.findAllById(ids);
    }

    public Optional<Materia> buscarCursoPorId(Long id_materia) { return materiaRepository.findById(id_materia);}

    public Materia atualizarMateria(DadosAtualizaMateria dados) {
        Materia materiaExistente = materiaRepository.findById(dados.id_materia())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        materiaExistente.atualizarMateria(dados);

        materiaRepository.save(materiaExistente);

        return materiaExistente;
    }

    public Optional<Materia> buscarMateriaPorId(Long id_materia) {
        return materiaRepository.findById(id_materia);
    }

    public Materia buscarMateria(Long id_materia) {
        Materia materiaExistente = materiaRepository.findById(id_materia)
                .orElseThrow(() -> new RuntimeException("Matéria não encontrado"));

        return materiaExistente;
    }
}
