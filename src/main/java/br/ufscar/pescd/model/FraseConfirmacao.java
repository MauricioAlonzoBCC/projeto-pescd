package br.ufscar.pescd.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "fraseconfirmacao")
public class FraseConfirmacao{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String mensagem;

    public FraseConfirmacao(){}
    public FraseConfirmacao(String frase){
        mensagem = frase;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String frase){
        mensagem = frase;
    }
}

