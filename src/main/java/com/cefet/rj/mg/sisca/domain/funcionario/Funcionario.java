package com.cefet.rj.mg.sisca.domain.funcionario;

import com.cefet.rj.mg.sisca.domain.usuario.RoleEnum;
import com.cefet.rj.mg.sisca.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "FUNCIONARIO")
@Entity(name = "Funcionario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_funcionario")
@DiscriminatorColumn(name="tipo_funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_funcionario;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;


    @Column(name = "matricula_funcionario")
    private int matricula_funcionario;

    public Funcionario(Usuario usuario, int matricula_funcionario) {
        this.usuario = usuario;
        this.matricula_funcionario = matricula_funcionario;
    }

    public boolean isProfessor(Long id_funcionario){
        return this.usuario.getRole() == RoleEnum.PROFESSOR;
    }

    public boolean isAlmoxarifado(Long id_funcionario){
        return this.usuario.getRole() == RoleEnum.ALMOXARIFE;
    }
}
