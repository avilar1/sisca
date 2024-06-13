package com.cefet.rj.mg.sisca.domain.professor;

import com.cefet.rj.mg.sisca.domain.funcionario.Funcionario;
import com.cefet.rj.mg.sisca.domain.funcionario.RoleTipoFuncionarioEnum;
import com.cefet.rj.mg.sisca.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("PROFESSOR")
public class Professor extends Funcionario{

    public Professor(Usuario usuario, int matricula_funcionario) {
        super(usuario, matricula_funcionario);
    }

}
