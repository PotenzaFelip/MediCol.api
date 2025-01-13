package medi.col.api.Medico;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medi.col.api.Endereco.Enderco;

@Table(name="medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Medico {
    public Medico(dadosCadastroMedico dados) {
        this.nome=dados.nome();
        this.email=dados.email();
        this.crm=dados.crm();
        this.telefone=dados.telefone();
        this.ativo=true;
        this.especialidade=dados.especialidade();
        this.endereco=new Enderco(dados.endereco());
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Enderco endereco;

    public void atualizarInfos(DadosAtualizar dados) {

        if(dados.nome() !=null )
            this.nome=dados.nome();
        if(dados.email()!=null)
            this.email=dados.email();
        if(dados.crm()!=null)
            this.crm=dados.crm();
        if(dados.especialidade()!=null)
            this.especialidade=dados.especialidade();
        if(dados.telefone() !=null )
            this.telefone=dados.telefone();
        // if(dados.endereoco()!=null)
        //     this.endereco.atualizarInfosEnd(dados.endereoco());

    }

    public void Excluir() {

        this.ativo=false;
    }

}
