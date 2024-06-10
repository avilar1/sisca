package com.cefet.rj.mg.sisca.domain.materia;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "MATERIA")
@Entity(name = "Materia")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_materia")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_materia;

    @Column(nullable = false)
    private String nome;


    public Materia(DadosCadastroMateria dados) {
        this.nome = dados.nome();

    }

    public void atualizarMateria(DadosAtualizaMateria dados){

    }

}
