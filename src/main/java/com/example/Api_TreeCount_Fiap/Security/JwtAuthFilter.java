package com.example.Api_TreeCount_Fiap.Security;

import com.example.Api_TreeCount_Fiap.Services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final List<String> publicEndpoints = Arrays.asList(
            "/api/auth/login",
            "/api/auth/register"
    );

    private final List<String> protectedEndpoints = Arrays.asList(
            "/api/historico",
            "/api/arvore"
    );

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Se for endpoint público, deixa passar
        if (isPublicEndpoint(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Se for protegido, exige token válido
        if (isProtectedEndpoint(path)) {
            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token JWT não fornecido");
                return;
            }

            String token = authHeader.substring(7);
            try {
                String username = jwtService.extractUsername(token);

                if (!jwtService.validateToken(token, username)) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token JWT inválido ou expirado");
                    return;
                }

                // Se quiser guardar o username como atributo da request:
                request.setAttribute("username", username);

            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Erro ao validar o token: " + e.getMessage());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean isPublicEndpoint(String path) {
        return publicEndpoints.stream().anyMatch(path::startsWith);
    }

    private boolean isProtectedEndpoint(String path) {
        return protectedEndpoints.stream().anyMatch(path::startsWith);
    }
}
