package com.cefet.rj.mg.sisca.domain.professor;

import com.cefet.rj.mg.sisca.domain.funcionario.Funcionario;
import com.cefet.rj.mg.sisca.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Professor")
@PrimaryKeyJoinColumn(name = "id_funcionario")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Professor extends Funcionario{

}
