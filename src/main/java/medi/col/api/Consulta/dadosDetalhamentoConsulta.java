package medi.col.api.Consulta;

import java.time.LocalDateTime;

public record dadosDetalhamentoConsulta(
    Long id, long idMedico,Long idPaciente, LocalDateTime data) {
    
}
