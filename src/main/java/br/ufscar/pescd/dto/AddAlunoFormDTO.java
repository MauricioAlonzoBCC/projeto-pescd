package br.ufscar.pescd.dto;
import jakarta.validation.constraints.NotNull;

public class AddAlunoFormDTO {

    @NotNull(message = "Selecione um aluno da lista")
    private Long alunoId;

    public Long getAlunoId() { return alunoId; }
    public void setAlunoId(Long alunoId) { this.alunoId = alunoId; }
}