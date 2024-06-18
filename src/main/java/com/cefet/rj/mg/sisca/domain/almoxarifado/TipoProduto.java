package com.cefet.rj.mg.sisca.domain.almoxarifado;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "TIPO_PRODUTO")
@Entity(name = "TipoProduto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_tipo_produto")
public class TipoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tipo_produto;

    @Column(nullable = false)
    private String nome;

    public TipoProduto(String nome) {
        this.nome = nome;
    }
}