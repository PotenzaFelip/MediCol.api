package medi.col.api.Medico;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedicoRepository extends JpaRepository<Medico,Long>{

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
        SELECT m FROM Medico m
        WHERE 
        m.ativo = true
        AND m.especialidade = :especialidade
        AND NOT EXISTS (
            SELECT c FROM Consulta c
            WHERE c.medico = m
            AND c.data = :data
        )
        ORDER BY FUNCTION('RAND')
        """)
    Medico escolhermedico(@Param("especialidade")Especialidade especialidade,@Param("data") LocalDateTime data);

    @Query("""
            select m.ativo
            from Medico m
            where
            m.id=:id
            """)
    Boolean findAtivoByLogin(@Param("id")long idMedico);
   
}
