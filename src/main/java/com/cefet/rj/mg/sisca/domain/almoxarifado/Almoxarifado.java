package com.cefet.rj.mg.sisca.domain.almoxarifado;

import com.cefet.rj.mg.sisca.domain.funcionario.Funcionario;
import com.cefet.rj.mg.sisca.domain.usuario.Usuario;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("ALMOXARIFADO")
public class Almoxarifado extends Funcionario {

    public Almoxarifado(Usuario usuario, int matricula_funcionario) {
        super(usuario, matricula_funcionario);
    }
}
