package medi.col.api.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import medi.col.api.Consulta.AgendaConsulta;
import medi.col.api.Consulta.DadosAgendamento;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaConsulta agenda;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> agendar(@RequestBody @Valid DadosAgendamento dados){
        try{
        agenda.agendar(dados);
        return ResponseEntity.ok("Consulta Cadastrada");
        }
        catch(Exception e)
        {   
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("success", false, "message", e.getMessage())
            );
        }
    }
}
