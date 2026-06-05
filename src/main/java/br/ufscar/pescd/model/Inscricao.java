package br.ufscar.pescd.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inscricao")
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Usuario aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oferta_id", nullable = false)
    private Oferta oferta;


    // Construtor vazio (obrigatório para o Spring/Hibernate funcionar)
    public Inscricao() {}


    public Inscricao(Usuario aluno, Oferta oferta) {
        this.aluno = aluno;
        this.oferta = oferta;
    }


    public Long getId() {
        return id;
    }

    public Usuario getAluno() {
        return aluno;
    }

    public Oferta getOferta() {
        return oferta;
    }



    public void setAluno(Usuario aluno) {
        this.aluno = aluno;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }
}