package com.cefet.rj.mg.sisca.domain.biblioteca;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "LIVRO")
@Entity(name = "Livro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_livro;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private String editor;


    public Livro(DadosCadastroLivro dados) {
        this.titulo = dados.titulo();
        this.autor = dados.autor();
        this.editor = dados.editor();
    }

    public void atualizarDados(DadosAtualizacaoLivro dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.autor() != null) {
            this.autor = dados.autor();
        }
        if (dados.editor() != null) {
            this.editor = dados.editor();
        }
    }
}