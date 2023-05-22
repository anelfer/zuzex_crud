package me.anelfer.zuzexcrud.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.anelfer.zuzexcrud.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final CitizenService citizenService;
    private final JWTComponent jwtComponent;

    @Autowired
    public JWTFilter(CitizenService citizenService, JWTComponent jwtComponent) {
        this.citizenService = citizenService;
        this.jwtComponent = jwtComponent;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        var authHeader = request.getHeader("Authorization");
        System.out.println("authHeader = " + authHeader);
        if (authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
            var token = authHeader.replace("Bearer ", "");
            if (token.isBlank()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT");
            } else {
                try {
                    var id = jwtComponent.validateTokenAndRetrieveSubject(token);
                    var userDetails = citizenService.loadUserByUsername(id);

                    System.out.println("userDetails = " + userDetails);
                    var authToken = new UsernamePasswordAuthenticationToken(userDetails, userDetails, Collections.emptyList());
                    System.out.println("authToken = " + authToken);
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    System.out.println("authentication = " + authentication);
                    if (authentication == null) {
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                    System.out.println("authentication = " + SecurityContextHolder.getContext().getAuthentication());
                } catch (Exception e) {
                    System.out.println("e = " + e);
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT");
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
