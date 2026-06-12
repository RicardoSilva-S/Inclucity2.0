package com.inclucity.web.repository;

import com.inclucity.web.model.Solicitacao;
import com.inclucity.web.model.StatusSolicitacao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Repositório em memória para persistência das solicitações.
 * Utiliza ConcurrentHashMap para garantir segurança em ambiente multithread.
 *
 * Responsabilidade única: apenas operações de acesso e armazenamento de dados.
 * Toda regra de negócio permanece na camada Service.
 */
@Repository
public class SolicitacaoRepository {

    private final ConcurrentHashMap<String, Solicitacao> armazenamento = new ConcurrentHashMap<>();

    /** Salva ou atualiza uma solicitação pelo protocolo. */
    public Solicitacao salvar(Solicitacao solicitacao) {
        armazenamento.put(solicitacao.getProtocolo(), solicitacao);
        return solicitacao;
    }

    /** Busca uma solicitação pelo protocolo único. */
    public Optional<Solicitacao> buscarPorProtocolo(String protocolo) {
        if (protocolo == null || protocolo.isBlank()) {
            return Optional.empty();
        }
        return Optional.ofNullable(armazenamento.get(protocolo.toUpperCase().trim()));
    }

    /** Retorna todas as solicitações ordenadas da mais recente para a mais antiga. */
    public List<Solicitacao> listarTodas() {
        return armazenamento.values().stream()
                .sorted((s1, s2) -> s2.getDataCriacao().compareTo(s1.getDataCriacao()))
                .collect(Collectors.toList());
    }

    /**
     * Filtra solicitações por categoria, localização e/ou status.
     * Parâmetros nulos ou vazios são ignorados (filtro não aplicado).
     */
    public List<Solicitacao> filtrar(String categoria, String localizacao, StatusSolicitacao status) {
        return armazenamento.values().stream()
                .filter(s -> isVazio(categoria) || s.getCategoria().equalsIgnoreCase(categoria))
                .filter(s -> isVazio(localizacao) || s.getLocalizacao().toLowerCase()
                                                       .contains(localizacao.toLowerCase()))
                .filter(s -> status == null || s.getStatusAtual().equals(status))
                .sorted((s1, s2) -> s2.getDataCriacao().compareTo(s1.getDataCriacao()))
                .collect(Collectors.toList());
    }

    private boolean isVazio(String valor) {
        return valor == null || valor.isBlank();
    }
}
