package com.inclucity.web.model;

/**
 * Define os possíveis estados de uma solicitação no sistema IncluCity.
 * Representa o ciclo de vida completo, desde a abertura até o fechamento.
 */
public enum StatusSolicitacao {
    ABERTO("Aberto"),
    TRIAGEM("Triagem"),
    EM_EXECUCAO("Em Execução"),
    RESOLVIDO("Resolvido"),
    ENCERRADO("Encerrado");

    private final String descricao;

    StatusSolicitacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
