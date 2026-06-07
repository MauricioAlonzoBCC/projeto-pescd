package br.ufscar.pescd.controllers;

import br.ufscar.pescd.model.Oferta;
import br.ufscar.pescd.services.OfertaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/responsavel")
public class ResponsavelController {

    @Autowired
    private OfertaService ofertaService;

    @GetMapping("/main")
    public String main(Model model) {

        model.addAttribute(
                "ofertas",
                ofertaService.listarPorFimMaisRecente()
        );

        return "responsavel/main";
    }

    @PostMapping("/finalizar/{id}")
    public String finalizarOferta(@PathVariable Long id) {

        Oferta oferta = ofertaService.buscarPorId(id);

        oferta.setStatus(
                "Aguardando encerramento do secretario"
        );

        ofertaService.salvar(oferta);

        return "redirect:/responsavel/main";
    }
}