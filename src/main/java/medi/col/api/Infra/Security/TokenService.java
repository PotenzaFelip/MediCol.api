package medi.col.api.Infra.Security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import jakarta.servlet.http.HttpServletRequest;
import medi.col.api.Usuario.Usuario;


@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    

    public String gerarToken(Usuario usuario){
        try {
             var algoritmo = Algorithm.HMAC256(secret);
            String token = JWT.create()
                              .withIssuer("API Medicol")
                              .withSubject(usuario.getLogin())
                              .withExpiresAt(dataExpiracao())
                              .sign(algoritmo);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao Gerar Token",exception);
        }
    }

     public String VerifyToken(HttpServletRequest request){
        try {
            String token = request.getHeader("Authorization").substring(7);
            if(token==null)
                throw new RuntimeException("Erro ao Validar Token");
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                      .withIssuer("API Medicol")
                      .build()
                      .verify(token)
                      .getSubject();
        }
        catch (JWTVerificationException exception){
                throw new RuntimeException("Erro ao Validar/Expirado Token",exception);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
