package com.cefet.rj.mg.sisca.domain.financeiro;

import com.cefet.rj.mg.sisca.domain.aluno.Aluno;
import com.cefet.rj.mg.sisca.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "BOLETOS")
@Entity(name = "Boleto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "codigo_de_barras")
public class Boleto {

    @Id
    @Column(name = "codigo_de_barras", nullable = false, unique = true)
    private String codigo_de_barras;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private float valor;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;

    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column(nullable = false)
    private float juros;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_boleto", nullable = false)
    private TipoBoletoEnum tipoBoleto;
}