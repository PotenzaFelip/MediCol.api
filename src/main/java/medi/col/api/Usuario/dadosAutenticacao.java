package medi.col.api.Usuario;

import com.fasterxml.jackson.annotation.JsonProperty;

public record dadosAutenticacao( @JsonProperty("login") String login,  @JsonProperty("senha") String senha) {

} 