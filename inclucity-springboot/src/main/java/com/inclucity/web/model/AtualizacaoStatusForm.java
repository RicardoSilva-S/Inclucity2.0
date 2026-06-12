package com.inclucity.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para receber os dados do formulário de atualização de status no painel gestor.
 */
public class AtualizacaoStatusForm {

    @NotBlank(message = "O protocolo é obrigatório.")
    private String protocolo;

    @NotNull(message = "O novo status é obrigatório.")
    private StatusSolicitacao novoStatus;

    @NotBlank(message = "O comentário é obrigatório ao atualizar o status.")
    @NotNull
    private String comentario;

    private String responsavel = "Gestor";

    // --- Getters e Setters ---

    public String getProtocolo()                 { return protocolo; }
    public void setProtocolo(String p)           { this.protocolo = p; }

    public StatusSolicitacao getNovoStatus()     { return novoStatus; }
    public void setNovoStatus(StatusSolicitacao s) { this.novoStatus = s; }

    public String getComentario()                { return comentario; }
    public void setComentario(String c)          { this.comentario = c; }

    public String getResponsavel()               { return responsavel; }
    public void setResponsavel(String r)         { this.responsavel = r; }
}
