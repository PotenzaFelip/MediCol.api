package medi.col.api.Consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import medi.col.api.Medico.Medico;
import medi.col.api.Medico.MedicoRepository;
import medi.col.api.Paciente.PacienteRepository;

@Service
public class AgendaConsulta  {

    @Autowired
    private ConsultaRepository repository;
    @Autowired
    private MedicoRepository MedicoRepository;
    @Autowired
    private PacienteRepository PacienteRepository;
    
    public void agendar(DadosAgendamento dados){

        if(!PacienteRepository.existsById(dados.idPaciente()))
            throw new RuntimeException("Erro ao Validar Paciente");
        if(dados.idMedico() != null && !MedicoRepository.existsById(dados.idMedico()))
            throw new RuntimeException("Erro ao Validar Medico");

        var paciente = PacienteRepository.getReferenceById(dados.idPaciente());
        var medico = EscolhaMedico(dados);
        var consulta = new Consulta(null,medico,paciente,dados.data());

        repository.save(consulta);
    }
        
    private Medico EscolhaMedico(DadosAgendamento dados) {

        if(dados.idMedico()!=null)
            return MedicoRepository.getReferenceById(dados.idMedico());
        if(dados.especialidade()==null)
            throw new RuntimeException("Especialidade não escolhida");

        return MedicoRepository.escolhermedico(dados.especialidade(),dados.data());
    }

}
