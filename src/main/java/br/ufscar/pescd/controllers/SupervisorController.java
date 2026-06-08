package br.ufscar.pescd.controllers;

import br.ufscar.pescd.dto.AprovarPlanoFormDTO;
import br.ufscar.pescd.dto.AprovarRelatorioFormDTO;
import br.ufscar.pescd.model.Inscricao;
import br.ufscar.pescd.model.StatusPlano;
import br.ufscar.pescd.services.InscricaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;
import br.ufscar.pescd.model.Usuario;
import br.ufscar.pescd.services.UsuarioService;

@Controller
@RequestMapping("/supervisor")
public class SupervisorController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private InscricaoService inscricaoService;

    @GetMapping("/main")
    public String main(Model model, Principal principal) {
        Usuario supervisor = usuarioService.buscarPorUsername(principal.getName());

        List<Inscricao> lista = inscricaoService.buscarPorSupervisor(supervisor);

        if (lista != null) {
            lista.forEach(i -> System.out.println("ID Inscrição: " + i.getId() + " - Status: " + i.getStatusPlano()));
        }

        model.addAttribute("inscricoes", lista);
        return "supervisor/main";
    }

    @GetMapping("/aprovar-plano/{id}")
    public String exibirFormularioAprovacao(@PathVariable("id") Long id, Model model) {
        Inscricao inscricao = inscricaoService.buscarPorID(id);

        // o status do aluno deve ser "plano enviado"
        if (inscricao.getStatusPlano() != StatusPlano.ENVIADO) {
            return "redirect:/supervisor/main?erro=status_invalido";
        }

        AprovarPlanoFormDTO form = new AprovarPlanoFormDTO();
        form.setInscricaoID(id);

        // injeta os dados da inscrição (para os campos de leitura) e o formulário
        model.addAttribute("inscricao", inscricao);
        model.addAttribute("aprovarPlanoForm", form);

        return "supervisor/aprovar_plano";
    }

    @PostMapping("/aprovar-plano")
    public String processarAprovacao(@Valid @ModelAttribute("aprovarPlanoForm") AprovarPlanoFormDTO form,
                                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            // caso de erro de validação, recarrega a página com as mensagens
            Inscricao inscricao = inscricaoService.buscarPorID(form.getInscricaoID());
            model.addAttribute("inscricao", inscricao);
            return "supervisor/aprovar_plano";
        }

        inscricaoService.aprovarPlano(form);
        return "redirect:/supervisor/main?sucesso=plano_aprovado";
    }

    @GetMapping("/aprovar-relatorio/{id}")
    public String exibirFormularioAprovacaoRelatorio(@PathVariable("id") Long id, Model model) {
        Inscricao inscricao = inscricaoService.buscarPorID(id);

        if (inscricao.getStatusPlano() != StatusPlano.RELATORIO_ENVIADO) {
            return "redirect:/supervisor/main?erro=status_invalido";
        }

        AprovarRelatorioFormDTO form = new AprovarRelatorioFormDTO();
        form.setInscricaoID(id);

        form.setFrequencia(inscricao.getFrequencia());

        model.addAttribute("inscricao", inscricao);
        model.addAttribute("aprovarRelatorioForm", form);

        return "supervisor/aprovar_relatorio";
    }

    @PostMapping("/aprovar-relatorio")
    public String processarAprovacaoRelatorio(@Valid @ModelAttribute("aprovarRelatorioForm") AprovarRelatorioFormDTO form,
                                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            Inscricao inscricao = inscricaoService.buscarPorID(form.getInscricaoID());
            model.addAttribute("inscricao", inscricao);
            return "supervisor/aprovar_relatorio";
        }

        inscricaoService.aprovarRelatorio(form);
        return "redirect:/supervisor/main?sucesso=relatorio_aprovado";
    }
}