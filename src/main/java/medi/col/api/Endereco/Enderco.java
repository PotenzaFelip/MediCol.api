package medi.col.api.Endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Enderco {
    
    public Enderco(dadosEndereco endereco) {
        this.logradouro=endereco.logradouro();
        this.bairro=endereco.bairro();
        this.cep=endereco.cep();
        this.uf=endereco.uf();
        this.cidade=endereco.cidade();
        this.complemento=endereco.complemento();
        this.numero=endereco.numero();
    }
    
    
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public void atualizarInfosEnd(Enderco endereco) {
        if(endereco.logradouro !=null)
            this.logradouro=endereco.logradouro;
        if(endereco.bairro !=null)
            this.bairro=endereco.bairro;
        if(endereco.cep !=null)
            this.cep=endereco.cep;
        if(endereco.numero !=null)
            this.numero=endereco.numero;
        if(endereco.complemento !=null)
            this.complemento=endereco.complemento;
        if(endereco.cidade !=null)
            this.cidade=endereco.cidade;
        if(endereco.uf !=null)
            this.uf=endereco.uf;
    }
}
