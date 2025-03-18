package medi.col.api.Consulta;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

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

        
        verificarMedico(dados);
        verificarPaciente(dados);
        ValidarHorarioAntecedencia(dados);
        ValidarHorarioFuncionamento(dados);

        var paciente = PacienteRepository.getReferenceById(dados.idPaciente());
        var medico = EscolhaMedico(dados);
        var consulta = new Consulta(null,medico,paciente,dados.data());
        
        repository.save(consulta);
    }

    private void ValidarHorarioFuncionamento(DadosAgendamento dados){
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horaAbertura = dataConsulta.getHour()<7;
        var horaFechamento = dataConsulta.getHour()>18;
        if(domingo || horaAbertura || horaFechamento)
            throw new RuntimeException("Consulta fora do horario de funcionamento");
    }

    private void ValidarHorarioAntecedencia(DadosAgendamento dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferenca= Duration.between(agora, dataConsulta).toMinutes();
        if(diferenca<30)
            throw new RuntimeException("Consulta deve ser feita com antecedencia minima de 30 minutos");
    }

    private void verificarPaciente(DadosAgendamento dados){

        if(!PacienteRepository.existsById(dados.idPaciente()))
            throw new RuntimeException("Erro ao Validar Paciente");

        var pacienteAtivo = PacienteRepository.findAtivoByLogin(dados.idMedico());
        if(!pacienteAtivo)
            throw new RuntimeException("Paciente invalido");
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        boolean jaPossuiConsultaMarca = repository.existsByConsulta(dados.idPaciente(),primeiroHorario,ultimoHorario);
        if(jaPossuiConsultaMarca)
            throw new RuntimeException("Paciente já possui consulta marcada nesse dia");
            
    }

    private void verificarMedico(DadosAgendamento dados){

        if(dados.idMedico() != null && !MedicoRepository.existsById(dados.idMedico()))
            throw new RuntimeException("Erro ao Validar Medico");

        if(dados.idMedico()==null)
            return;

        var medicoAtivo = MedicoRepository.findAtivoByLogin(dados.idMedico());
        if(!medicoAtivo)
            throw new RuntimeException("Medico invalido");

        boolean medicoConsulta= repository.existsByMedicoIdAndData(dados.idMedico(),dados.data());
        if(medicoConsulta)
            throw new RuntimeException("Medico já possui consulta agendada para esse horario");
            
    }
    
        
    private Medico EscolhaMedico(DadosAgendamento dados) {

        if(dados.idMedico()!=null)
            return MedicoRepository.getReferenceById(dados.idMedico());
        if(dados.especialidade()==null)
            throw new RuntimeException("Especialidade não escolhida");

        return MedicoRepository.escolhermedico(dados.especialidade(),dados.data());
    }

}
