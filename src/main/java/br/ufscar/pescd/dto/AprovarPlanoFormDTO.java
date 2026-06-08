package br.ufscar.pescd.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AprovarPlanoFormDTO {

    @NotNull
    private Long inscricaoID;

    @NotBlank(message = "O parecer é obrigatório")
    private String parecer;

    public Long getInscricaoID() { return inscricaoID; }
    public void setInscricaoID(Long inscricaoID) { this.inscricaoID = inscricaoID; }

    public String getParecer() { return parecer; }
    public void setParecer(String parecer) { this.parecer = parecer; }
}