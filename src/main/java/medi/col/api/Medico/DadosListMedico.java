package medi.col.api.Medico;

public record DadosListMedico(long id,String nome,String email,String crm, Especialidade especialidade,boolean ativo) {

    public DadosListMedico(Medico medico){
        this(medico.getId(),medico.getNome(),medico.getEmail(),medico.getCrm(),medico.getEspecialidade(),medico.getAtivo());
    }
}
