package com.cefet.rj.mg.sisca.domain.financeiro;

import com.cefet.rj.mg.sisca.domain.funcionario.Funcionario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "FOLHA_DE_PAGAMENTO")
@Entity(name = "FolhaDePagamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_folha_de_pagamento")
public class FolhaDePagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_folha_de_pagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario_pagante", nullable = false)
    private Funcionario funcionarioPagante;

    @Column(nullable = false)
    private float valorPago;

    @Column(name = "data_pagamento", nullable = false)
    private LocalDate dataPagamento;
}
