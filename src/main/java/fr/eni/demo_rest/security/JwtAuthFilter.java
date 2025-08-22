package fr.eni.demo_rest.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.eni.demo_rest.service.ServiceHelper;
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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (true){
            // Forcer la réponse http à être en JSON
            response.setContentType("application/json");

            // il faut ecrire dans le body de la réponse ton message métier
            // Object mapper pour mettre du contenu dans la réponse
            ServiceResponse serviceResponse = ServiceHelper.buildResponse("704", "You shall not pass");

            objectMapper.writeValue(response.getWriter(), serviceResponse);

            return;
        }

        // passer (Oui/Ok)
        filterChain.doFilter(request, response);
    }
}
