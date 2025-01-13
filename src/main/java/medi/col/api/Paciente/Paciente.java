package medi.col.api.Paciente;

import java.sql.Date;

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

@Table(name="Pacientes")
@Entity(name = "Pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Paciente {
    public Paciente (dadosCadastroPaciente Dados){
        this.nome=Dados.nome();
        this.email=Dados.email();
        this.cpf=Dados.cpf();
        this.data_nascimento=Dados.data_nascimento();
        this.telefone=Dados.telefone();
        this.ativo=true;
        this.genero=Dados.genero();
        this.data_registro=Dados.data_registro();
        this.endereco=new Enderco(Dados.endereco());
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cpf;
    private Date data_nascimento;
    private String telefone;
    private String email;
    @Embedded
    private Enderco endereco;

    @Enumerated(EnumType.STRING)
    private Genero genero;
    private Date data_registro;
    private Boolean ativo;

    public void AtualizarPaciente(DadosAtualizarPacientes dados){
        if(dados.nome()!=null)
            this.nome=dados.nome();
        if(dados.cpf()!=null)
            this.cpf=dados.cpf();
        if(dados.email()!=null)
            this.email=dados.email();
        if(dados.data_nascimento()!=null)
            this.data_nascimento=dados.data_nascimento();
        if(dados.telefone()!=null)
            this.telefone=dados.telefone();
        if(dados.genero()!=null)
            this.genero=dados.genero();
        if(dados.data_registro()!=null)
            this.data_registro=dados.data_registro();
    }
    
    public void Excluir(){
        this.ativo=false;
    }
}
