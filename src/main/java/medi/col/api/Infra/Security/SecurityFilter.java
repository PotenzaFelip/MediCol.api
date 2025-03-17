package medi.col.api.Infra.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import medi.col.api.Usuario.UsuarioRepository;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    
    @Value("${api.security.token.secret}")
    private String secret;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository repository;

   
    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if(request.getHeader("Authorization")!=null){
            var userName=tokenService.VerifyToken(request);
            var usuario= repository.findByLogin(userName);
            var authentication= new UsernamePasswordAuthenticationToken(usuario, null,usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

   
}
