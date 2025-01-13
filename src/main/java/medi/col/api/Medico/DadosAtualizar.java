package medi.col.api.Medico;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizar(
    
    @NotNull
    long id,
    String nome,
    String email,
    String crm,
    String telefone,
    boolean ativo,
    Especialidade especialidade
    ) {

}
