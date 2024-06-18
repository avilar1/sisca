package com.cefet.rj.mg.sisca.domain.almoxarifado;

import jakarta.persistence.*;
import lombok.*;
@Table(name = "PRODUTO")
@Entity(name = "Produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_produto;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_tipo_produto", nullable = false)
    private TipoProduto tipoProduto;

    public Produto(DadosCadastroProduto dados, TipoProduto tipoProduto) {
        this.nome = dados.nome();
        this.tipoProduto = tipoProduto;
    }
}
