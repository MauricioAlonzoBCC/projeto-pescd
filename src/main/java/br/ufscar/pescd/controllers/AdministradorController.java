package br.ufscar.pescd.controllers;

import br.ufscar.pescd.model.FraseConfirmacao;
import br.ufscar.pescd.repositories.FraseRepository;
import br.ufscar.pescd.services.OfertaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    private FraseRepository fraseRepository;

    @GetMapping("/main")
    public String main() {
        return "administrador/main";
    }

    @PostMapping("/alterarMensagem")
    public String alterarMensagem(String texto) {

        FraseConfirmacao mensagem =
                fraseRepository.findById(1L)
                        .orElseThrow();

        mensagem.setMensagem(texto);

        fraseRepository.save(mensagem);

        return "redirect:/administrador/main";
    }
}