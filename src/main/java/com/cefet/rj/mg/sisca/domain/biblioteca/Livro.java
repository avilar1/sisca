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

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, length = 150)
    private String autor;

    @Column(nullable = false, length = 150)
    private String editor;
}