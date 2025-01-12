package medi.col.api.Medico;

import jakarta.validation.constraints.NotNull;
import medi.col.api.Endereco.Enderco;

public record DadosAtualizar(
    
    @NotNull long id, String nome, String telefone,Enderco endereoco) {

}
