package com.inclucity.web.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa um registro histórico de mudança de status de uma solicitação.
 * Armazena o rastro de auditoria: quem alterou, quando e o motivo.
 */
public class HistoricoStatus {

    private final StatusSolicitacao statusAnterior;
    private final StatusSolicitacao statusAtual;
    private final String comentario;
    private final String responsavel;
    private final LocalDateTime dataHoraRegistro;

    public HistoricoStatus(StatusSolicitacao statusAnterior,
                           StatusSolicitacao statusAtual,
                           String comentario,
                           String responsavel) {
        this.statusAnterior = statusAnterior;
        this.statusAtual = statusAtual;
        this.comentario = comentario;
        this.responsavel = responsavel;
        this.dataHoraRegistro = LocalDateTime.now();
    }

    public StatusSolicitacao getStatusAnterior() { return statusAnterior; }
    public StatusSolicitacao getStatusAtual()    { return statusAtual; }
    public String getComentario()                { return comentario; }
    public String getResponsavel()               { return responsavel; }
    public LocalDateTime getDataHoraRegistro()   { return dataHoraRegistro; }

    /** Retorna a data/hora formatada para exibição no padrão brasileiro. */
    public String getFormattedDataHora() {
        return dataHoraRegistro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
