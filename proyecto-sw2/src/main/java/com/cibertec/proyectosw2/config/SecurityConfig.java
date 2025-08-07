package com.cibertec.proyectosw2.config;

import com.cibertec.proyectosw2.security.JwtAuthenticationFilter;
import com.cibertec.proyectosw2.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;
    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Habilita CORS con la configuración de CorsConfig
                .cors(Customizer.withDefaults())
                // Deshabilita CSRF para APIs REST
                .csrf(csrf -> csrf.disable())
                // Stateless: no guarda sesión en servidor
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // Reglas de acceso
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/api/usuarios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,  "/api/productos/**").authenticated()
                        .requestMatchers("/api/productos/**").hasRole("ADMIN")
                        .requestMatchers("/api/producciones/**").hasAnyRole("OPERADOR","ADMIN")
                        .requestMatchers("/api/ventas/**").hasAnyRole("VENDEDOR","ADMIN")
                        .requestMatchers("/api/mermas/**").hasAnyRole("OPERADOR","ADMIN")
                        .anyRequest().authenticated()
                )
                // Agrega el filtro JWT **antes** del de username/password
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
