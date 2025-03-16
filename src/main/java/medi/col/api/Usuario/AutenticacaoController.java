package medi.col.api.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import medi.col.api.Infra.Security.DadosTokenJWT;
import medi.col.api.Infra.Security.TokenService;


@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService TokenService;
    
    @SuppressWarnings("rawtypes")
    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid dadosAutenticacao dados){

        try {
            var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
            var authentication = manager.authenticate(token);  // Autenticação do usuário
            var jwtToken = TokenService.gerarToken((Usuario)authentication.getPrincipal());
            return ResponseEntity.ok(new DadosTokenJWT(jwtToken));
        } catch (BadCredentialsException e) {
            // Se as credenciais estiverem incorretas
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                 .body("Acesso negado. Credenciais inválidas.");
        } catch (Exception e) {
            // Para outros erros inesperados
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erro interno no servidor.");
        }

    }
    @PostMapping("/hash")
    public ResponseEntity<String> gerarHashSenha(@RequestBody @Valid dadosAutenticacao dados) {
        String senhaCriptografada = passwordEncoder.encode(dados.senha()); // Usando o PasswordEncoder injetado
        return ResponseEntity.ok(senhaCriptografada); // Retorna a senha criptografada
    }
}
