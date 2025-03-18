package medi.col.api.Consulta;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

   boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);

   @Query("""
           SELECT COUNT(c) > 0 
           FROM Consulta c 
           WHERE c.paciente.id = :idPaciente
           AND c.data BETWEEN :primeiroHorario AND :ultimoHorario
         """)
   boolean existsByConsulta(
            @Param("idPaciente") Long idPaciente, 
            @Param("primeiroHorario") LocalDateTime primeiroHorario, 
            @Param("ultimoHorario") LocalDateTime ultimoHorario
   );

}
