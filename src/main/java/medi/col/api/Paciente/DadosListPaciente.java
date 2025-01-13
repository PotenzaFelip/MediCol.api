package medi.col.api.Paciente;

import java.sql.Date;


public record DadosListPaciente(long id, String nome, String email, String cpf, Date data_nascimento, Date data_registro, String telefone, Boolean ativo, Genero genero) {
    public DadosListPaciente(Paciente paciente){
        this(paciente.getId(),paciente.getNome(),paciente.getEmail(),
        paciente.getCpf(),paciente.getData_nascimento(),paciente.getData_registro(),paciente.getTelefone(),paciente.getAtivo(),paciente.getGenero());
    }
}
