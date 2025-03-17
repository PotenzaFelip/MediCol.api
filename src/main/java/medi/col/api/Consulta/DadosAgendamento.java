package medi.col.api.Consulta;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import medi.col.api.Medico.Especialidade;

public record DadosAgendamento(
    Long idMedico,
        
    @NotNull 
    Long idPaciente,

    @NotNull
    @Future
    LocalDateTime data,
    
    Especialidade especialidade) {

}
