package com.fbs.central_api.filter;

import com.fbs.central_api.services.UserService;
import com.fbs.central_api.utility.AuthUtility;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.Collections;

@Component
public class AuthFilter extends OncePerRequestFilter {

    UserService userService;
    AuthUtility authUtility;

    @Autowired
    public AuthFilter(UserService userService, AuthUtility authUtility){
        this.userService=userService;
        this.authUtility=authUtility;
    }
    // If the token is valid, then we will be setting username password authentication and calling the doFilter method
    // If token is in-valid then, without setting username password authentication, we are directly calling doFilter

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader("Authorization");
        
        if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);
            // We got the token now we need to validate that this token is a genuine token or not.
            try {
                boolean isValid = userService.validateToken(token);
                if (!isValid) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
                String payload = authUtility.decryptJwtToken(token);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(payload, null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        
        // Always call filterChain.doFilter to continue the request processing
        // Spring Security will handle authorization based on the SecurityFilterChain configuration
        filterChain.doFilter(request, response);
    }
}
