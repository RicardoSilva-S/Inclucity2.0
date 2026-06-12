package com.inclucity.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO (Data Transfer Object) para receber os dados do formulário de nova solicitação.
 * Desacopla o modelo de domínio da camada de apresentação.
 */
public class SolicitacaoForm {

    @NotBlank(message = "A categoria é obrigatória.")
    private String categoria;

    @NotBlank(message = "A descrição é obrigatória.")
    @Size(min = 10, message = "A descrição deve ter pelo menos 10 caracteres.")
    private String descricao;

    @NotBlank(message = "A localização é obrigatória.")
    private String localizacao;

    private boolean anonima;
    private String nomeCidadao;
    private String contatoCidadao;

    // --- Getters e Setters ---

    public String getCategoria()              { return categoria; }
    public void setCategoria(String c)        { this.categoria = c; }

    public String getDescricao()              { return descricao; }
    public void setDescricao(String d)        { this.descricao = d; }

    public String getLocalizacao()            { return localizacao; }
    public void setLocalizacao(String l)      { this.localizacao = l; }

    public boolean isAnonima()                { return anonima; }
    public void setAnonima(boolean a)         { this.anonima = a; }

    public String getNomeCidadao()            { return nomeCidadao; }
    public void setNomeCidadao(String n)      { this.nomeCidadao = n; }

    public String getContatoCidadao()         { return contatoCidadao; }
    public void setContatoCidadao(String c)   { this.contatoCidadao = c; }
}
