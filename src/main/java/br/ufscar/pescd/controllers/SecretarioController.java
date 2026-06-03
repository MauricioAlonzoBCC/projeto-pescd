package br.ufscar.pescd.controllers;

import br.ufscar.pescd.model.FraseConfirmacao;
import br.ufscar.pescd.model.Oferta;
import br.ufscar.pescd.model.Usuario;
import br.ufscar.pescd.repositories.FraseRepository;
import br.ufscar.pescd.services.OfertaService;
import br.ufscar.pescd.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/secretario")
public class SecretarioController {

    @Autowired
    private OfertaService ofertaService;
    @Autowired
    private FraseRepository fraseRepository;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/main")
    public String main(Model model) {

        model.addAttribute(
                "ofertas",
                ofertaService.listarPorFimMaisRecente()
        );

        FraseConfirmacao mensagem =
                fraseRepository
                        .findAll()
                        .stream()
                        .findFirst()
                        .orElseThrow();

        model.addAttribute(
                "mensagem",
                mensagem.getMensagem()
        );

        return "secretario/main";
    }

    @PostMapping("/encerrar/{id}")
    public String encerrarOferta(@PathVariable Long id) {

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        Oferta oferta = ofertaService.buscarPorId(id);

        oferta.setStatus("Concluida");

        oferta.setEncerramento(LocalDateTime.now());

        Usuario usuario =
                usuarioService.buscarPorUsername(auth.getName());

        oferta.setEncerradoPor(usuario.getNome());

        ofertaService.salvar(oferta);

        return "redirect:/secretario/main";
    }
}