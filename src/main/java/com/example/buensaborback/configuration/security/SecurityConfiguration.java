package com.example.buensaborback.configuration.security;

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
                                //Endpoints cerrados y el rol que tiene acceso a ellos
                                //ARTICULO INSUMO
                                .requestMatchers(HttpMethod.POST, "ArticuloInsumo/save").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "ArticuloInsumo/save/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/ArticuloInsumo/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/ArticuloInsumo/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/ArticuloInsumo").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/ArticuloInsumo/eliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/ArticuloInsumo/noEliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/ArticuloInsumo/activate/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                //ARTICULO MANUFACTURADO
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




                                .requestMatchers(HttpMethod.GET, "/ArticuloManufacturadoDetalle/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/ArticuloManufacturadoDetalle").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/ArticuloManufacturadoDetalle/eliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/ArticuloManufacturadoDetalle/noEliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/ArticuloManufacturadoDetalle").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/ArticuloManufacturadoDetalle/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/ArticuloManufacturadoDetalle/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/ArticuloManufacturadoDetalle/activate/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/categoria/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/categoria").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/categoria/eliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/categoria/noEliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/categoria").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/categoria/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/categoria/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/categoria/activate/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/cliente/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/cliente").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/cliente/eliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/cliente/noEliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/cliente").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/cliente/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/cliente/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/cliente/activate/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/detallePedido/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/detallePedido").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/detallePedido/eliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/detallePedido/noEliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/detallePedido").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/detallePedido/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/detallePedido/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/detallePedido/activate/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/domicilio/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/domicilio").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/domicilio/eliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/domicilio/noEliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/domicilio").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/domicilio/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/domicilio/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/domicilio/activate/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/empleado/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/empleado").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/empleado/eliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/empleado/noEliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/empleado").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/empleado/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/empleado/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/empleado/activate/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/empresa/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/empresa").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/empresa/eliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/empresa/noEliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/empresa").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/empresa/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/empresa/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/empresa/activate/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/factura/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/factura").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/factura/eliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/factura/noEliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/factura").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/factura/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/factura/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/factura/activate/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/localidad/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/localidad").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/localidad/eliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/localidad/noEliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/localidad").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/localidad/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/localidad/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/localidad/activate/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/pais/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/pais").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/pais/eliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/pais/noEliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/pais").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/pais/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/pais/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/pais/activate/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/pedido/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/pedido").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/pedido/eliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/pedido/noEliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/pedido").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/pedido/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/pedido/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/pedido/activate/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/promocion/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/promocion").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/promocion/eliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/promocion/noEliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/promocion").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/promocion/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/promocion/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/promocion/activate/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/pomocionDetalle/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/pomocionDetalle").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/pomocionDetalle/eliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/pomocionDetalle/noEliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/pomocionDetalle").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/pomocionDetalle/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/pomocionDetalle/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/pomocionDetalle/activate/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/sucursal/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/sucursal").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/sucursal/eliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/sucursal/noEliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/sucursal").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/sucursal/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/sucursal/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/sucursal/activate/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/UnidadMedida/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/UnidadMedida").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/UnidadMedida/eliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/UnidadMedida/noEliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/UnidadMedida").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/UnidadMedida/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/UnidadMedida/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/UnidadMedida/activate/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/usuarioCliente/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/usuarioCliente").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/usuarioCliente/eliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/usuarioCliente/noEliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/usuarioCliente").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/usuarioCliente/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/usuarioCliente/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/usuarioCliente/activate/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/usuarioEmpleado/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/usuarioEmpleado").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/usuarioEmpleado/eliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/usuarioEmpleado/noEliminados").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/usuarioEmpleado").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/usuarioEmpleado/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/usuarioEmpleado/{id}").hasAnyAuthority("ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/usuarioEmpleado/activate/{id}").hasAnyAuthority("ADMIN", "COCINERO")
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
/*
El cocinero no deberia crear insumos pero si verlos, si puede crear manufacturados
 */