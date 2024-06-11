package com.example.buensaborback.configuration.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
import java.util.List;

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
                .csrf(csrf -> csrf
                        .disable())
                .cors(withDefaults())
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                /*
                                .requestMatchers(HttpMethod.POST, "ArticuloInsumo/save/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "ArticuloInsumo/save/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/ArticuloInsumo/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/ArticuloInsumo/activate/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/ArticuloManufacturado/save/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/ArticuloManufacturado/save").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/ArticuloManufacturado/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/categoria/addInsumo/{idCategoria}/{idInsumo}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/categoria/addArticuloManufacturado/{idCategoria}/{idArticulo}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/categoria/addSubCategoria/{idCategoria}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/categoria/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/cliente/{id}").hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/cliente").hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/empleado/getPorSucursal/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/empleado/save/{id}").hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/empleado/save").hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/pedido/getPorCliente/{id}").hasAnyAuthority("ADMIN", "COCINERO", "CAJERO", "DELIVERY")
                                .requestMatchers(HttpMethod.GET, "/pedido/{id}").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/pedido/cambiarEstado/{id}").hasAnyAuthority("ADMIN", "COCINERO", "CAJERO", "DELIVERY")
                                .requestMatchers(HttpMethod.POST, "/pedido").authenticated()
                                .requestMatchers(HttpMethod.POST, "/pedido/api/create_preference_mp").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/pedido/cancelar/{id}").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/promocion/save/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/promocion/save").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/sucursal/save").hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/UnidadMedida/**").hasAnyAuthority("ADMIN", "COCINERO", "CAJERO")
                                .requestMatchers(HttpMethod.POST, "/UnidadMedida").hasAnyAuthority("ADMIN", "COCINERO", "CAJERO")
                                .requestMatchers(HttpMethod.PUT, "/UnidadMedida").hasAnyAuthority("ADMIN", "COCINERO", "CAJERO")
                                .requestMatchers(HttpMethod.DELETE, "/UnidadMedida").hasAuthority("ADMIN")
*/
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
    /*
                                .requestMatchers("/empresa/save/**").hasAuthority("ADMIN")

                                .requestMatchers("/ArticuloInsumo/**").hasAuthority("ADMIN")
                                .requestMatchers("/ArticuloManufacturado/**").hasAuthority("ADMIN")
                                .requestMatchers("/ArticuloManufacturadoDetalle/**").hasAuthority("ADMIN")
                                .requestMatchers("/categoria/**").hasAuthority("ADMIN")
                                .requestMatchers("/cliente/**").hasAuthority("ADMIN")
                                .requestMatchers("/detallePedido/**").hasAuthority("ADMIN")
                                .requestMatchers("/domicilio/**").hasAuthority("ADMIN")
                                .requestMatchers("/empleado/**").hasAuthority("ADMIN")
                                .requestMatchers("/empresa/**").hasAuthority("ADMIN")
                                .requestMatchers("/factura/**").hasAuthority("ADMIN")
                                .requestMatchers("/imagenArticulo/**").hasAuthority("ADMIN")
                                .requestMatchers("/imagenCliente/**").hasAuthority("ADMIN")
                                .requestMatchers("/imagenEmpleado/**").hasAuthority("ADMIN")
                                .requestMatchers("/imagenEmpresa/**").hasAuthority("ADMIN")
                                .requestMatchers("/imagenPromocion/**").hasAuthority("ADMIN")
                                .requestMatchers("/imagenSucursal/**").hasAuthority("ADMIN")
                                .requestMatchers("/localidad/**").hasAuthority("ADMIN")
                                .requestMatchers("/mercadoPago/**").hasAuthority("ADMIN")
                                .requestMatchers("/pais/**").hasAuthority("ADMIN")
                                .requestMatchers("/pedido/**").hasAuthority("ADMIN")
                                .requestMatchers("/preferenceMP/**").hasAuthority("ADMIN")
                                .requestMatchers("/promocion/**").hasAuthority("ADMIN")
                                .requestMatchers("/promocionDetalle/**").hasAuthority("ADMIN")
                                .requestMatchers("/provincia/**").hasAuthority("ADMIN")
                                .requestMatchers("/sucursal/**").hasAuthority("ADMIN")
                                .requestMatchers("/UnidadMedida/**").hasAuthority("ADMIN")
                                .requestMatchers("/usuarioCliente/**").hasAuthority("ADMIN")
                                .requestMatchers("/usuarioEmpleado/**").hasAuthority("ADMIN")
*/


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(corsAllowedOrigins.split(",")));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setExposedHeaders(List.of("X-Get-Header"));
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
        //IMPORTANTE se hace asi porque la variable de entorno tiene una / al final, si le saco esa / no funciona nada pero si
        //la tiene esto no funciona
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