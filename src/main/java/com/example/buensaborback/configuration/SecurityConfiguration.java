package com.example.buensaborback.configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Value("${auth0.audience}")
    private String audience;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    @Value("${web.cors.allowed-origins}")
    private String corsAllowedOrigins;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(withDefaults()) // Por defecto Spring va a buscar un bean con el nombre "corsConfigurationSource"
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/ArticuloInsumo/**").hasAuthority("ADMIN")
                                .requestMatchers("/ArticuloManufacturado/**").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers("/ArticuloManufacturadoDetalle/**").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers("/categoria/**").hasAuthority("COCINERO")
                                .requestMatchers("/cliente/**").hasAnyAuthority("ADMIN", "CAJERO")
                                .requestMatchers("/detallePedido/**").hasAnyAuthority("ADMIN", "CAJERO")
                                .requestMatchers("/domicilio/**").hasAnyAuthority("ADMIN", "CAJERO")
                                .requestMatchers("/empleado/**").hasAnyAuthority("ADMIN", "COCINERO", "CAJERO", "DELIVERY")
                                .requestMatchers("/empresa/**").hasAuthority("COCINERO")
                                .requestMatchers("/factura/**").hasAnyAuthority("ADMIN", "CAJERO")
                                .requestMatchers("/imagenArticulo/**").hasAuthority("ADMIN")
                                .requestMatchers("/imagenCliente/**").hasAnyAuthority("ADMIN", "CAJERO")
                                .requestMatchers("/imagenEmpleado/**").hasAnyAuthority("ADMIN", "COCINERO", "CAJERO", "DELIVERY")
                                .requestMatchers("/imagenEmpresa/**").hasAuthority("ADMIN")
                                .requestMatchers("/imagenPromocion/**").hasAuthority("ADMIN")
                                .requestMatchers("/imagenSucursal/**").hasAuthority("ADMIN")
                                .requestMatchers("/localidad/**").permitAll()
                                .requestMatchers("/mercadoPago/**").hasAnyAuthority("ADMIN", "CAJERO")
                                .requestMatchers("/pais/**").permitAll()
                                .requestMatchers("/pedido/**").hasAnyAuthority("ADMIN", "CAJERO")
                                .requestMatchers("/preferenceMP/**").hasAnyAuthority("ADMIN", "CAJERO")
                                .requestMatchers("/promocion/**").hasAuthority("ADMIN")
                                .requestMatchers("/promocionDetalle/**").hasAuthority("ADMIN")
                                .requestMatchers("/provincia/**").permitAll()
                                .requestMatchers("/sucursal/**").hasAuthority("CONINERO")
                                .requestMatchers("/UnidadMedida/**").hasAuthority("COCINERO")
                                .requestMatchers("/usuarioCliente/**").hasAnyAuthority("ADMIN", "CAJERO")
                                .requestMatchers("/usuarioEmpleado/**").hasAnyAuthority("ADMIN", "COCINERO", "CAJERO", "DELIVERY")
                                .anyRequest().permitAll() // Cualquier otro, tiene que estar al menos autenticado, es decir, que tenga un jwt válido
                )
                .oauth2ResourceServer(oauth2ResourceServer ->
                        oauth2ResourceServer
                                .jwt(jwt -> jwt
                                        .decoder(jwtDecoder())
                                        .jwtAuthenticationConverter(jwtAuthenticationConverter())
                                )
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(corsAllowedOrigins.split(",")));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setExposedHeaders(Arrays.asList("X-Get-Header"));
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuer);

        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthoritiesClaimName(audience + "/roles");
        converter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(converter);
        return jwtConverter;
    }
}
/*ESTAS SON LAS VARIABLES DE ENTORNO
    AUTH0_AUDIENCE=http://api_auth0_buenSabor/;AUTH0_ISSUER_URI=https://dev-ni2b3u711x5zx2x7.us.auth0.com/;CORS_ALLOWED_ORIGINS=http://localhost:5173;SPRING_SECURITY_DEBUG=INFO;WEB_SECURITY_DEBUG=true
 */