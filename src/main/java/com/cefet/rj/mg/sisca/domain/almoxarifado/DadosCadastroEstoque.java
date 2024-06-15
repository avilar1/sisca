package com.cefet.rj.mg.sisca.domain.almoxarifado;

import java.util.Date;

public record DadosCadastroEstoque(
        Long idProduto,
        Long idFuncionario,
        Float quantidade,
        String descricao,
        Date dataTransacao
) {
}
