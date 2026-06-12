package com.inclucity.web.controller;

import com.inclucity.web.model.AtualizacaoStatusForm;
import com.inclucity.web.model.Solicitacao;
import com.inclucity.web.model.StatusSolicitacao;
import com.inclucity.web.service.SolicitacaoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

/**
 * Controller do painel do gestor/atendente.
 * Responsabilidade: listar, filtrar e atualizar status das solicitações.
 */
@Controller
@RequestMapping("/solicitacoes/painel")
public class PainelGestorController {

    private static final List<String> CATEGORIAS = Arrays.asList(
            "Iluminação Pública", "Buraco na Rua", "Limpeza Urbana",
            "Saúde", "Segurança Escolar", "Acessibilidade", "Outros"
    );

    private final SolicitacaoService solicitacaoService;

    public PainelGestorController(SolicitacaoService solicitacaoService) {
        this.solicitacaoService = solicitacaoService;
    }

    // -----------------------------------------------------------------------
    // Listar / Filtrar
    // -----------------------------------------------------------------------

    @GetMapping
    public String exibirPainel(@RequestParam(required = false) String categoria,
                               @RequestParam(required = false) String localizacao,
                               @RequestParam(required = false) StatusSolicitacao status,
                               Model model) {

        List<Solicitacao> solicitacoes = solicitacaoService.filtrar(categoria, localizacao, status);

        model.addAttribute("solicitacoes", solicitacoes);
        model.addAttribute("categorias", CATEGORIAS);
        model.addAttribute("todosStatus", StatusSolicitacao.values());
        model.addAttribute("statusAtualizaveis", obterStatusAtualizaveis());
        model.addAttribute("atualizacaoForm", new AtualizacaoStatusForm());

        // Preserva os filtros aplicados na view
        model.addAttribute("filtroCategoria", categoria);
        model.addAttribute("filtroLocalizacao", localizacao);
        model.addAttribute("filtroStatus", status);

        return "painel-gestor";
    }

    // -----------------------------------------------------------------------
    // Atualizar Status
    // -----------------------------------------------------------------------

    @PostMapping("/atualizar")
    public String atualizarStatus(@Valid @ModelAttribute("atualizacaoForm") AtualizacaoStatusForm form,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("erroAtualizacao", "Preencha todos os campos obrigatórios.");
            return "redirect:/solicitacoes/painel";
        }

        try {
            solicitacaoService.atualizarStatus(
                    form.getProtocolo(),
                    form.getNovoStatus(),
                    form.getComentario(),
                    form.getResponsavel()
            );
            redirectAttributes.addFlashAttribute("sucesso", "Status atualizado com sucesso!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("erroAtualizacao", e.getMessage());
        }

        return "redirect:/solicitacoes/painel";
    }

    /** Retorna os status disponíveis para atualização manual pelo gestor. */
    private List<StatusSolicitacao> obterStatusAtualizaveis() {
        return Arrays.asList(
                StatusSolicitacao.TRIAGEM,
                StatusSolicitacao.EM_EXECUCAO,
                StatusSolicitacao.RESOLVIDO,
                StatusSolicitacao.ENCERRADO
        );
    }
}
