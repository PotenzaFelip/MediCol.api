package medi.col.api.Paciente;

import java.sql.Date;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import medi.col.api.Endereco.dadosEndereco;

public record dadosCadastroPaciente(
    @NotBlank
    String nome,
    Boolean ativo,
    @NotBlank
    String email,
    @NotBlank
    String cpf,
    @NotNull
    Date data_nascimento,
    @NotBlank
    String telefone,
    @NotNull
    Genero genero,
    @NotNull
    Date data_registro,
    @NotNull @Valid
    dadosEndereco endereco) {


}
