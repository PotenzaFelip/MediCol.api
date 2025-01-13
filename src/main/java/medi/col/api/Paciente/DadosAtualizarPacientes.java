package medi.col.api.Paciente;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarPacientes (
    @NotNull
    Long id,
    String nome,
    Boolean ativo,
    String email,
    String cpf,
    Date data_nascimento,
    String telefone,
    Genero genero,
    Date data_registro
    //dadosEndereco endereco

){

}
