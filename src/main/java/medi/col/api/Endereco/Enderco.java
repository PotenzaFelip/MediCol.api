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
    
    @SuppressWarnings("unused")
    private String logradouro;
    @SuppressWarnings("unused")
    private String bairro;
    @SuppressWarnings("unused")
    private String cep;
    @SuppressWarnings("unused")
    private String numero;
    @SuppressWarnings("unused")
    private String complemento;
    @SuppressWarnings("unused")
    private String cidade;
    @SuppressWarnings("unused")
    private String uf;
}
