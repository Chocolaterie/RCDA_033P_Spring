package fr.eni.demo_rest.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.eni.demo_rest.service.AuthService;
import fr.eni.demo_rest.service.ServiceResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Si l'url est different de /api/create-token -> verifier le token
        String url = request.getRequestURI();

        if (!url.startsWith("/api/create-token")) {
            // Récupérer le token
            String token = request.getHeader("Authorization");

            // Appeler notre service qui check le token
            ServiceResponse<Boolean> serviceResponse = authService.checkToken(token);

            // Si pas bon (!= 202 code métier)
            if (!serviceResponse.code.equals("202")) {
                // Forcer la réponse http à être en JSON
                response.setContentType("application/json");

                objectMapper.writeValue(response.getWriter(), serviceResponse);
                return;
            }
        }

        // passer (Oui/Ok)
        filterChain.doFilter(request, response);
    }
}
