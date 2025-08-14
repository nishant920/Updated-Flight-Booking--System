package com.fbs.central_api.configuration;

import com.fbs.central_api.filter.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configurable
public class AuthConfiguration {

    @Autowired
    AuthFilter authFilter;

    /**
     * In this SecurityFilterChain we are declaring what all endpoints will be not secured.
     * Other then that all the endpoints will be secured.
     * @param http
     * @return
     * @throws Exception
     */
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // updated syntax
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/v1/central/user/login",
                                "/api/v1/central/airline/register"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
