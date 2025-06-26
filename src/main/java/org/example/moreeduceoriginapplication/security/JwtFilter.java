package org.example.moreeduceoriginapplication.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService customUserDetailsService;
    private final AuthenticationManager authenticationManager;

    public JwtFilter(JwtProvider jwtProvider, JwtProvider jwtProvider1, CustomUserDetailsService customUserDetailsService, AuthenticationManager authenticationManager, AuthenticationManager authenticationManager1) {
        this.jwtProvider = jwtProvider1;
        this.customUserDetailsService = customUserDetailsService;
        this.authenticationManager = authenticationManager1;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization!=null&&authorization.startsWith("Bearer ")) {

            String token = authorization.substring(7);

            if (jwtProvider.isValid(token)){
                String email = jwtProvider.extractEmail(token);
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

                Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));

                SecurityContextHolder.getContext().setAuthentication(authenticate);



            }

        }

        filterChain.doFilter(request,response);
    }
}
