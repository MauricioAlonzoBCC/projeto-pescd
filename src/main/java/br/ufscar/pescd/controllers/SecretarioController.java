package br.ufscar.pescd.controllers;

import br.ufscar.pescd.dto.OfertaFormDTO;
import br.ufscar.pescd.model.FraseConfirmacao;
import br.ufscar.pescd.model.Oferta;
import br.ufscar.pescd.model.Usuario;
import br.ufscar.pescd.repositories.FraseRepository;
import br.ufscar.pescd.services.OfertaService;
import br.ufscar.pescd.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

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


    @GetMapping("/criar_oferta")
    public String criarOferta(Model model) {

        model.addAttribute(
                "professores",
                usuarioService.filtrarPorCargo("ROLE_RESPONSAVEL")
        );



        model.addAttribute(
                "ofertaFormDTO",
                new OfertaFormDTO());
        return "secretario/criar_oferta";
    }

    //@Valid aplica restricoes do DTO nos campos recebidos
    @PostMapping("/criar_oferta")
    public String salvarNovaOferta(
            @Valid @ModelAttribute("ofertaFormDTO")OfertaFormDTO dto,
            BindingResult result,
            Model model){

        //verifica se inicio e fim estao ok
        if(dto.getInicio() != null && dto.getFim() != null){
            if(dto.getInicio().isAfter(dto.getFim())){
                // anota erro no binding result
                result.rejectValue("fim", "error.ofertaFormDTO",
                        "A data de fim não pode ser anterior à data de início.");
            }
        }

        //Se houver erros volta para mesma tela
        if(result.hasErrors()){
            model.addAttribute("professores",
                    usuarioService.filtrarPorCargo("ROLE_RESPONSAVEL"));
                return "secretario/criar_oferta";
        }

        //se tudo ok:
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usernameSecretario = auth.getName();

        ofertaService.salvarOferta(dto, usernameSecretario);
        return "redirect:/secretario/main";


    }
}