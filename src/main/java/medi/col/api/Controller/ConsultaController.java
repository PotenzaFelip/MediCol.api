package medi.col.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import medi.col.api.Consulta.AgendaConsulta;
import medi.col.api.Consulta.DadosAgendamento;
import medi.col.api.Consulta.dadosDetalhamentoConsulta;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaConsulta agenda;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> agendar(@RequestBody @Valid DadosAgendamento dados){
        agenda.agendar(dados);
        return ResponseEntity.ok(new dadosDetalhamentoConsulta(null,dados.idMedico(),dados.idPaciente(),dados.data()));
        //return ResponseEntity.ok().build();
    }
}
