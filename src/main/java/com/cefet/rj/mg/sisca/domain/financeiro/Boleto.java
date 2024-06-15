package com.cefet.rj.mg.sisca.domain.financeiro;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "BOLETOS")
@Entity(name = "Boleto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "codigoDeBarras")
public class Boleto {

    @Id
    @Column(name = "codigo_de_barras", nullable = false, unique = true, length = 13)
    private String codigoDeBarras;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(nullable = false)
    private float valor;

    @Column(name = "data_criacao", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataCriacao;

    @Column(name = "data_vencimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;

    @Column(name = "data_pagamento")
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;

    @Column(nullable = false)
    private Float juros;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_boleto", nullable = false)
    private TipoBoletoEnum tipoBoleto;

    public Boleto(DadosCadastroBoleto dados) {
        this.codigoDeBarras = generateCodigoDeBarras();
        this.idUsuario = dados.idUsuario();
        this.valor = dados.valor();
        this.dataCriacao = dados.dataCriacao();
        this.dataVencimento = dados.dataVencimento();
        this.juros = dados.juros();
        this.tipoBoleto = dados.tipoBoleto();
    }

    private String generateCodigoDeBarras() {
        // Implementação fictícia para gerar um código de barras único
        return "1234567890123";
    }
}