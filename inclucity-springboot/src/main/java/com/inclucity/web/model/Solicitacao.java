package com.inclucity.web.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Representa uma solicitação de serviço urbano no sistema IncluCity.
 * Centraliza as informações do problema reportado, dados do cidadão e o rastreio de status.
 */
public class Solicitacao {

    private final String protocolo;
    private final String categoria;
    private final String descricao;
    private final String localizacao;
    private final LocalDateTime dataCriacao;
    private StatusSolicitacao statusAtual;
    private final boolean ehAnonima;
    private final String nomeCidadao;
    private final String contatoCidadao;
    private final List<HistoricoStatus> historicoMovimentacoes;

    /**
     * Cria uma nova solicitação, gerando protocolo único e registrando o status inicial.
     */
    public Solicitacao(String categoria, String descricao, String localizacao,
                       boolean ehAnonima, String nomeCidadao, String contatoCidadao) {
        this.protocolo = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.categoria = categoria;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.dataCriacao = LocalDateTime.now();
        this.statusAtual = StatusSolicitacao.ABERTO;
        this.ehAnonima = ehAnonima;
        this.nomeCidadao = nomeCidadao;
        this.contatoCidadao = contatoCidadao;
        this.historicoMovimentacoes = new ArrayList<>();
        this.historicoMovimentacoes.add(
                new HistoricoStatus(null, StatusSolicitacao.ABERTO, "Solicitação criada.", "Sistema")
        );
    }

    // --- Getters ---

    public String getProtocolo()              { return protocolo; }
    public String getCategoria()              { return categoria; }
    public String getDescricao()              { return descricao; }
    public String getLocalizacao()            { return localizacao; }
    public LocalDateTime getDataCriacao()     { return dataCriacao; }
    public StatusSolicitacao getStatusAtual() { return statusAtual; }
    public boolean isEhAnonima()              { return ehAnonima; }
    public String getNomeCidadao()            { return nomeCidadao; }
    public String getContatoCidadao()         { return contatoCidadao; }

    /** Retorna cópia imutável do histórico para evitar modificações externas. */
    public List<HistoricoStatus> getHistoricoMovimentacoes() {
        return Collections.unmodifiableList(historicoMovimentacoes);
    }

    /** Retorna a data de criação formatada no padrão brasileiro (dd/MM/yyyy HH:mm). */
    public String getFormattedDataCriacao() {
        return dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Atualiza o status da solicitação e registra a movimentação no histórico.
     *
     * @param novoStatus  Novo estado da solicitação.
     * @param comentario  Justificativa técnica ou observação da atualização.
     * @param responsavel Nome do agente ou sistema que realizou a alteração.
     */
    public void atualizarStatus(StatusSolicitacao novoStatus, String comentario, String responsavel) {
        StatusSolicitacao statusAnterior = this.statusAtual;
        this.statusAtual = novoStatus;
        this.historicoMovimentacoes.add(
                new HistoricoStatus(statusAnterior, novoStatus, comentario, responsavel)
        );
    }
}
