package com.inclucity.web.controller;

import com.inclucity.web.model.Solicitacao;
import com.inclucity.web.model.SolicitacaoForm;
import com.inclucity.web.service.SolicitacaoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Controller responsável pelos fluxos do cidadão:
 * abrir solicitação, consultar protocolo e visualizar detalhes.
 */
@Controller
@RequestMapping("/solicitacoes")
public class SolicitacaoController {

    private static final List<String> CATEGORIAS = Arrays.asList(
            "Iluminação Pública",
            "Buraco na Rua",
            "Limpeza Urbana",
            "Saúde",
            "Segurança Escolar",
            "Acessibilidade",
            "Outros"
    );

    private final SolicitacaoService solicitacaoService;

    public SolicitacaoController(SolicitacaoService solicitacaoService) {
        this.solicitacaoService = solicitacaoService;
    }

    // -----------------------------------------------------------------------
    // Nova Solicitação
    // -----------------------------------------------------------------------

    @GetMapping("/nova")
    public String exibirFormulario(Model model) {
        model.addAttribute("solicitacaoForm", new SolicitacaoForm());
        model.addAttribute("categorias", CATEGORIAS);
        return "nova-solicitacao";
    }

    @PostMapping("/nova")
    public String processarFormulario(@Valid @ModelAttribute("solicitacaoForm") SolicitacaoForm form,
                                      BindingResult bindingResult,
                                      Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categorias", CATEGORIAS);
            return "nova-solicitacao";
        }
        Solicitacao nova = solicitacaoService.criarSolicitacao(form);
        return "redirect:/solicitacoes/confirmacao?protocolo=" + nova.getProtocolo();
    }

    // -----------------------------------------------------------------------
    // Confirmação
    // -----------------------------------------------------------------------

    @GetMapping("/confirmacao")
    public String exibirConfirmacao(@RequestParam String protocolo, Model model) {
        model.addAttribute("protocolo", protocolo);
        return "confirmacao-solicitacao";
    }

    // -----------------------------------------------------------------------
    // Consultar por Protocolo
    // -----------------------------------------------------------------------

    @GetMapping("/consultar")
    public String exibirConsulta() {
        return "consultar-solicitacao";
    }

    @PostMapping("/consultar")
    public String processarConsulta(@RequestParam String protocolo, Model model) {
        Optional<Solicitacao> encontrada = solicitacaoService.buscarPorProtocolo(protocolo);
        if (encontrada.isPresent()) {
            return "redirect:/solicitacoes/detalhes?protocolo=" + protocolo;
        }
        model.addAttribute("erro", "Protocolo não encontrado. Verifique o número e tente novamente.");
        return "consultar-solicitacao";
    }

    // -----------------------------------------------------------------------
    // Detalhes da Solicitação
    // -----------------------------------------------------------------------

    @GetMapping("/detalhes")
    public String exibirDetalhes(@RequestParam String protocolo, Model model) {
        Solicitacao solicitacao = solicitacaoService.buscarPorProtocolo(protocolo)
                .orElse(null);

        if (solicitacao == null) {
            return "redirect:/solicitacoes/consultar";
        }
        model.addAttribute("solicitacao", solicitacao);
        return "detalhes-solicitacao";
    }
}
