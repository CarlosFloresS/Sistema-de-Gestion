package com.cibertec.proyectosw2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.*;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cfg = new CorsConfiguration();

        // Orígenes permitidos (tu front Angular en localhost:4200)
        cfg.setAllowedOriginPatterns(List.of("http://localhost:4200"));
        // Métodos HTTP permitidos
        cfg.setAllowedMethods(List.of(
                "GET","POST","PUT","PATCH","DELETE","OPTIONS"
        ));
        // Cabeceras que el cliente puede enviar
        cfg.setAllowedHeaders(List.of(
                "Authorization","Content-Type","Accept"
        ));
        // Cabeceras que el cliente puede leer de la respuesta
        cfg.setExposedHeaders(List.of(
                "Authorization","Content-Type"
        ));
        // Permitir credenciales si las usas
        cfg.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Aplica esta configuración a todas las rutas
        source.registerCorsConfiguration("/**", cfg);
        return source;
    }
}
