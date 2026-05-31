package br.ufscar.pescd.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "oferta")
public class Oferta{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private Date inicio;

    @Column(nullable = false, unique = false)
    private Date fim;

    @Column(nullable = false, unique = false)
    private String nome;

    @Column(nullable = false, unique = false)
    private String semestre;

    @Column(nullable = false, unique = false)
    private String prof;

    @Column(nullable = false, unique = false)
    private int matriculados;

    public Oferta(){}

    public Oferta(Long id, Date inicio, Date fim, String nome, String semestre, String prof, int matriculados){
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
        this.nome = nome;
        this.semestre = semestre;
        this.prof = prof;
        this.matriculados = matriculados;
    }

    public Long getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }
    public Date getInicio(){
        return inicio;
    }
    public Date getFim(){
        return fim;
    }
    public String getSemestre(){
        return semestre;
    }
    public String getProf(){
        return prof;
    }

    public int getMatriculados(){
        return matriculados;
    }

}

