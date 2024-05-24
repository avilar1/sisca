package com.cefet.rj.mg.sisca.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "USUARIO")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    //private String senha;

    private String nome;
    private String senha;
    private String cpf;
    private String email;
    private String telefone;
    private LocalDate data_nascimento;
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public Usuario(DadosCadastroUsuario dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.senha = dados.senha();
        this.cpf = dados.cpf();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.data_nascimento = LocalDate.from(dados.data_nascimento());
        this.role = dados.role();
    }

    public void excluir() {
        this.ativo = false;
    }
}
