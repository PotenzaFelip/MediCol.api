package medi.col.api.Medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import medi.col.api.Endereco.dadosEndereco;

public record dadosCadastroMedico(
    
    @NotBlank
    String nome,

    @NotBlank
    @Email
    String email,

    @NotBlank
    @Pattern(regexp = "\\d{4,6}")
    String crm,
    
    @NotBlank
    String telefone,

    boolean ativo,

    @NotNull
    Especialidade especialidade,

    @NotNull @Valid
    dadosEndereco endereco) {

}