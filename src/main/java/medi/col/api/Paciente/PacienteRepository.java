package medi.col.api.Paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface PacienteRepository extends JpaRepository<Paciente,Long> {

    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);

     @Query("""
            select p.ativo
            from Pacientes p
            where
            p.id=:id
            """)
    Boolean findAtivoByLogin(@Param("id")Long idPaciente);    
}
