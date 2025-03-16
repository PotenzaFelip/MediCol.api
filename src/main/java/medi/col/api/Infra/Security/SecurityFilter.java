package medi.col.api.Infra.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    
    @Value("${api.security.token.secret}")
    private String secret;
    @Autowired
    private TokenService tokenService;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        if (requestURI.startsWith("/swagger-ui") || 
            requestURI.startsWith("/v3/api-docs") || 
            requestURI.startsWith("/login") ||
            requestURI.startsWith("/swagger-resources")) {
            filterChain.doFilter(request, response);
            return;
        }
        var userName=tokenService.VerifyToken(request);
        if(userName!=null)
            System.out.println(userName);
        filterChain.doFilter(request, response);
    }

   
}
