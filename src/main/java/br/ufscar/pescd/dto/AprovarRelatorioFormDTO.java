package br.ufscar.pescd.dto;

import jakarta.validation.constraints.*;

public class AprovarRelatorioFormDTO {

    private Long inscricaoID;

    @NotBlank(message = "O parecer é obrigatório")
    private String parecer;

    @NotNull(message = "A frequência é obrigatória")
    @Min(value = 0, message = "A frequência não pode ser menor que 0")
    @Max(value = 100, message = "A frequência não pode ser maior que 100")
    private Integer frequencia;

    @NotBlank(message = "A sugestão de nota é obrigatória")
    @Pattern(regexp = "^[A-E]$", message = "A nota deve ser A, B, C, D ou E")
    private String nota;

    public Long getInscricaoID() { return inscricaoID; }
    public void setInscricaoID(Long inscricaoID) { this.inscricaoID = inscricaoID; }

    public String getParecer() { return parecer; }
    public void setParecer(String parecer) { this.parecer = parecer; }

    public Integer getFrequencia() { return frequencia; }
    public void setFrequencia(Integer frequencia) { this.frequencia = frequencia; }

    public String getNota() { return nota; }
    public void setNota(String nota) { this.nota = nota; }
}