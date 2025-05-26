package com.example.taskmanagmentsystem.config;

import com.example.taskmanagmentsystem.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    public JwtAuthFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String token = extractToken(request);

        if (token != null && jwtUtils.validateToken(token)) {
            String username = request.getParameter("username");
            try {
                username = jwtUtils.getLoginFromToken(token);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            var authentication = new UsernamePasswordAuthenticationToken(
                    username, null, List.of() // pusta lista uprawnień
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        System.out.println("JWT Auth Filter: " + request.getRequestURI());
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        System.out.println("Authorization header: " + header);
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
