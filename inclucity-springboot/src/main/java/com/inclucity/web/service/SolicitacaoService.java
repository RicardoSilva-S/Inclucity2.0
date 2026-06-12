package com.inclucity.web.service;

import com.inclucity.web.model.Solicitacao;
import com.inclucity.web.model.SolicitacaoForm;
import com.inclucity.web.model.StatusSolicitacao;
import com.inclucity.web.repository.SolicitacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Camada de serviço: orquestra as regras de negócio das solicitações.
 * Atua como intermediária entre os Controllers e o Repositório.
 *
 * Nenhum controller deve conter lógica de negócio — toda decisão fica aqui.
 */
@Service
public class SolicitacaoService {

    private final SolicitacaoRepository repository;

    public SolicitacaoService(SolicitacaoRepository repository) {
        this.repository = repository;
    }

    /**
     * Cria e persiste uma nova solicitação a partir do formulário submetido.
     * Aplica regra de anonimato: se anônima, ignora nome e contato.
     */
    public Solicitacao criarSolicitacao(SolicitacaoForm form) {
        String nome    = form.isAnonima() ? null : form.getNomeCidadao();
        String contato = form.isAnonima() ? null : form.getContatoCidadao();

        Solicitacao nova = new Solicitacao(
                form.getCategoria(),
                form.getDescricao(),
                form.getLocalizacao(),
                form.isAnonima(),
                nome,
                contato
        );
        return repository.salvar(nova);
    }

    /**
     * Busca uma solicitação pelo protocolo.
     * Retorna Optional vazio se não encontrada.
     */
    public Optional<Solicitacao> buscarPorProtocolo(String protocolo) {
        return repository.buscarPorProtocolo(protocolo);
    }

    /** Retorna todas as solicitações ordenadas da mais recente para a mais antiga. */
    public List<Solicitacao> listarTodas() {
        return repository.listarTodas();
    }

    /**
     * Filtra solicitações com base em critérios opcionais.
     * Parâmetros nulos ou vazios são ignorados.
     */
    public List<Solicitacao> filtrar(String categoria, String localizacao, StatusSolicitacao status) {
        return repository.filtrar(categoria, localizacao, status);
    }

    /**
     * Atualiza o status de uma solicitação existente.
     * Lança exceção se o protocolo não for encontrado.
     */
    public void atualizarStatus(String protocolo, StatusSolicitacao novoStatus,
                                String comentario, String responsavel) {
        Solicitacao solicitacao = repository.buscarPorProtocolo(protocolo)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Protocolo não encontrado: " + protocolo));

        solicitacao.atualizarStatus(novoStatus, comentario, responsavel);
    }
}
