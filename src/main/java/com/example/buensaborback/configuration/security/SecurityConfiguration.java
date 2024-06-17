package com.example.buensaborback.configuration.security;
/*
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
                                //Endpoints cerrados y el rol que tiene acceso a ellos
                                //ARTICULO INSUMO
                                .requestMatchers(HttpMethod.GET, "/ArticuloInsumo/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/ArticuloInsumo").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/ArticuloInsumo/getArticulos/{idSucursal}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/ArticuloInsumo/getArticulosInsumos/{idSucursal}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/ArticuloInsumo/buscar/{idSucursal}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/ArticuloInsumo/eliminados").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/ArticuloInsumo/noEliminados").permitAll()
                                .requestMatchers(HttpMethod.POST, "ArticuloInsumo/save").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.POST, "ArticuloInsumo").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.POST, "/ArticuloInsumo/activate/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.PUT, "ArticuloInsumo/save/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.PUT, "ArticuloInsumo/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/ArticuloInsumo/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                //ARTICULO MANUFACTURADO
                                .requestMatchers(HttpMethod.GET, "/ArticuloManufacturado/getArticulosManufacturados/{idSucursal}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/ArticuloManufacturado/buscar/{idSucursal}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/ArticuloManufacturado/eliminados").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/ArticuloManufacturado/noEliminados").permitAll()
                                .requestMatchers(HttpMethod.GET, "/ArticuloManufacturado").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/ArticuloManufacturado/{id}").permitAll()
                                .requestMatchers(HttpMethod.POST, "/ArticuloManufacturado").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/ArticuloManufacturado/save").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/ArticuloManufacturado/activate/{id}").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/ArticuloManufacturado/save/{id}").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/ArticuloManufacturado/{id}").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/ArticuloManufacturado/{id}").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                //ARTICULO MANUFACTURADO DETALLE
                                .requestMatchers(HttpMethod.GET, "/ArticuloManufacturadoDetalle").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/ArticuloManufacturadoDetalle/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/ArticuloManufacturadoDetalle/eliminados").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/ArticuloManufacturadoDetalle/noEliminados").permitAll()
                                .requestMatchers(HttpMethod.POST, "/ArticuloManufacturadoDetalle").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/ArticuloManufacturadoDetalle/{id}").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/ArticuloManufacturadoDetalle/{id}").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/ArticuloManufacturadoDetalle/activate/{id}").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                //CATEGORIA
                                .requestMatchers(HttpMethod.PUT, "/categoria/addInsumo/{idCategoria}/{idInsumo}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/categoria/addArticuloManufacturado/{idCategoria}/{idArticulo}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/categoria/addSubCategoria/{idCategoria}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/categoria/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/categoria/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/categoria").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/categoria/getInsumos/{idCategoria}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/categoria/getManufacturados/{idCategoria}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/categoria/insumo").permitAll()
                                .requestMatchers(HttpMethod.GET, "/categoria/noInsumo").permitAll()
                                .requestMatchers(HttpMethod.GET, "/categoria/getCategoriasSinArticulos").permitAll()
                                .requestMatchers(HttpMethod.GET, "/categoria/eliminados").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/categoria/noEliminados").permitAll()
                                .requestMatchers(HttpMethod.POST, "/categoria").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/categoria/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.POST, "/categoria/activate/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                //CLIENTE
                                .requestMatchers(HttpMethod.GET, "/cliente/{id}").hasAnyAuthority("SUPERADMIN")
                                .requestMatchers(HttpMethod.GET, "/cliente").hasAnyAuthority("SUPERADMIN")
                                .requestMatchers(HttpMethod.GET, "/cliente/exists/{nombreUsuario}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/cliente/eliminados").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/cliente/noEliminados").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.POST, "/cliente").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.POST, "/cliente/create").permitAll()
                                .requestMatchers(HttpMethod.POST, "/cliente/save").permitAll()
                                .requestMatchers(HttpMethod.POST, "/cliente/getCliente").permitAll()
                                .requestMatchers(HttpMethod.POST, "/cliente/activate/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/cliente/{id}").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/cliente/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                //DETALLE PEDIDO
                                .requestMatchers(HttpMethod.GET, "/detallePedido/{id}").hasAnyAuthority("SUPERADMIN","ADMIN","COCINERO")
                                .requestMatchers(HttpMethod.GET, "/detallePedido").hasAnyAuthority("SUPERADMIN","ADMIN","COCINERO")
                                .requestMatchers(HttpMethod.GET, "/detallePedido/eliminados").hasAnyAuthority("SUPERADMIN","ADMIN","COCINERO")
                                .requestMatchers(HttpMethod.GET, "/detallePedido/noEliminados").hasAnyAuthority("SUPERADMIN","ADMIN","COCINERO")
                                .requestMatchers(HttpMethod.POST, "/detallePedido").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/detallePedido/{id}").hasAnyAuthority("SUPERADMIN","ADMIN","COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/detallePedido/{id}").hasAnyAuthority("SUPERADMIN","ADMIN","COCINERO")
                                .requestMatchers(HttpMethod.POST, "/detallePedido/activate/{id}").hasAnyAuthority("SUPERADMIN","ADMIN","COCINERO")
                                //DOMICILIO
                                .requestMatchers(HttpMethod.GET, "/domicilio/{id}").authenticated()
                                .requestMatchers(HttpMethod.GET, "/domicilio").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/domicilio/eliminados").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/domicilio/noEliminados").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.POST, "/domicilio").authenticated()
                                .requestMatchers(HttpMethod.POST, "/domicilio/addDomicilioCliente/{idCliente}").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/domicilio/{id}").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/domicilio/{id}").authenticated()
                                .requestMatchers(HttpMethod.POST, "/domicilio/activate/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                //EMPLEADO
                                .requestMatchers(HttpMethod.GET, "/empleado/getPorSucursal/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/empleado/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/empleado").hasAnyAuthority("SUPERADMIN")
                                .requestMatchers(HttpMethod.GET, "/empleado/role").permitAll()
                                .requestMatchers(HttpMethod.GET, "/empleado/getEmpleados").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/empleado/eliminados").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/empleado/noEliminados").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.POST, "/empleado").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.POST, "/empleado/getPorMail").authenticated()
                                .requestMatchers(HttpMethod.POST, "/empleado/save").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.POST, "/empleado/activate/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/empleado/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/empleado/save/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/empleado/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                //EMPRESA
                                .requestMatchers(HttpMethod.GET, "/empresa/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/empresa").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/empresa/eliminados").hasAnyAuthority("SUPERADMIN")
                                .requestMatchers(HttpMethod.GET, "/empresa/noEliminados").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/empresa/sucursales/{idEmpresa}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.POST, "/empresa").hasAnyAuthority("SUPERADMIN")
                                .requestMatchers(HttpMethod.POST, "/empresa/activate/{id}").hasAnyAuthority("SUPERADMIN")
                                .requestMatchers(HttpMethod.POST, "/empresa/save").hasAnyAuthority("SUPERADMIN")
                                .requestMatchers(HttpMethod.PUT, "/empresa/{id}").hasAnyAuthority("SUPERADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/empresa/{id}").hasAnyAuthority("SUPERADMIN")
                                //FACTURA
                                .requestMatchers(HttpMethod.GET, "/factura/{id}").authenticated()
                                .requestMatchers(HttpMethod.GET, "/factura").hasAnyAuthority("SUPERADMIN","ADMIN", "CAJERO")
                                .requestMatchers(HttpMethod.GET, "/factura/eliminados").hasAnyAuthority("SUPERADMIN","ADMIN", "CAJERO")
                                .requestMatchers(HttpMethod.GET, "/factura/noEliminados").hasAnyAuthority("SUPERADMIN","ADMIN", "CAJERO")
                                .requestMatchers(HttpMethod.POST, "/factura").hasAnyAuthority("SUPERADMIN","ADMIN", "CAJERO")
                                .requestMatchers(HttpMethod.PUT, "/factura/{id}").hasAnyAuthority("SUPERADMIN","ADMIN", "CAJERO")
                                .requestMatchers(HttpMethod.DELETE, "/factura/{id}").hasAnyAuthority("SUPERADMIN","ADMIN", "CAJERO")
                                .requestMatchers(HttpMethod.POST, "/factura/activate/{id}").hasAnyAuthority("SUPERADMIN","ADMIN", "CAJERO")
                                //LOCALIDAD
                                .requestMatchers(HttpMethod.GET, "/localidad/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/localidad").permitAll()
                                .requestMatchers(HttpMethod.GET, "/localidad/findByProvincia/{idProvincia}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/localidad/eliminados").hasAnyAuthority("SUPERADMIN")
                                .requestMatchers(HttpMethod.GET, "/localidad/noEliminados").permitAll()
                                .requestMatchers(HttpMethod.POST, "/localidad").hasAnyAuthority("SUPERADMIN")
                                .requestMatchers(HttpMethod.PUT, "/localidad/{id}").hasAnyAuthority("SUPERADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/localidad/{id}").hasAnyAuthority("SUPERADMIN")
                                .requestMatchers(HttpMethod.POST, "/localidad/activate/{id}").hasAnyAuthority("SUPERADMIN")
                                //PAIS
                                .requestMatchers(HttpMethod.GET, "/pais/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/pais").permitAll()
                                .requestMatchers(HttpMethod.GET, "/pais/eliminados").hasAnyAuthority("SUPERADMIN")
                                .requestMatchers(HttpMethod.GET, "/pais/noEliminados").permitAll()
                                .requestMatchers(HttpMethod.POST, "/pais").hasAnyAuthority("SUPERADMIN")
                                .requestMatchers(HttpMethod.PUT, "/pais/{id}").hasAnyAuthority("SUPERADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/pais/{id}").hasAnyAuthority("SUPERADMIN")
                                .requestMatchers(HttpMethod.POST, "/pais/activate/{id}").hasAnyAuthority("SUPERADMIN")
                                //PEDIDO
                                .requestMatchers(HttpMethod.GET, "/pedido/ingresos/{idSucursal}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/pedido/cantidad-pedidos-por-cliente/{idSucursal}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/pedido/ganancia/{idSucursal}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/pedido/ranking-articulos/{idSucursal}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/pedido/ranking-promociones/{idSucursal}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/pedido/getPorFecha/{idSucursal}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/pedido/getPorCliente/{id}").authenticated()
                                .requestMatchers(HttpMethod.GET, "/pedido/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/pedido").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/pedido/eliminados").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/pedido/noEliminados").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/pedido/getPorFechaYClienteYEstado/{idSucursal}/{idCliente}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.POST, "/pedido").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.POST, "/pedido/activate/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.POST, "/pedido/api/create_preference_mp").authenticated()
                                .requestMatchers(HttpMethod.POST, "/pedido/getPorEstado/{estado}/{idCliente}").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/pedido/cancelar/{id}").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/pedido/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/pedido/cambiarEstado/{id}").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO", "CAJERO", "DELIVERY")
                                .requestMatchers(HttpMethod.PUT, "/pedido/asignarEmpleado/{idEmpleado}/{idPedido}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/pedido/cancelar/{id}").hasAnyAuthority("SUPERADMIN","ADMIN","CAJERO","CLIENTE","DELIVERY")
                                .requestMatchers(HttpMethod.PUT, "/pedido/pendiente/{id}").hasAnyAuthority("SUPERADMIN","ADMIN","CAJERO","CLIENTE","DELIVERY")
                                .requestMatchers(HttpMethod.DELETE, "/pedido/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                //PROMOCIOCION
                                .requestMatchers(HttpMethod.GET, "/promocion/{id}").authenticated()
                                .requestMatchers(HttpMethod.GET, "/promocion").authenticated()
                                .requestMatchers(HttpMethod.GET, "/promocion/eliminados").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/promocion/noEliminados").authenticated()
                                .requestMatchers(HttpMethod.POST, "/promocion").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/promocion/{id}").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/promocion/{id}").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/promocion/activate/{id}").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/promocion/save/{id}").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/promocion/save").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                //PROMOCION DETALLE
                                .requestMatchers(HttpMethod.GET, "/pomocionDetalle/{id}").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/pomocionDetalle").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/pomocionDetalle/eliminados").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.GET, "/pomocionDetalle/noEliminados").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/pomocionDetalle").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.PUT, "/pomocionDetalle/{id}").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.DELETE, "/pomocionDetalle/{id}").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                .requestMatchers(HttpMethod.POST, "/pomocionDetalle/activate/{id}").hasAnyAuthority("SUPERADMIN","ADMIN", "COCINERO")
                                //PROVINCIA
                                .requestMatchers(HttpMethod.GET, "/provincia/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/provincia").permitAll()
                                .requestMatchers(HttpMethod.GET, "/provincia/eliminados").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/provincia/noEliminados").permitAll()
                                .requestMatchers(HttpMethod.POST, "/provincia").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.POST, "/provincia/activate/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/provincia/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/provincia/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                //SUCURSAL
                                .requestMatchers(HttpMethod.GET, "/sucursal/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/sucursal").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/sucursal/eliminados").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/sucursal/noEliminados").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/sucursal/getInsumos/{idSucursal}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/sucursal/getCategorias/{idSucursal}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/sucursal/getPromociones/{idSucursal}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.POST, "/sucursal").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/sucursal/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/sucursal/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.POST, "/sucursal/activate/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.POST, "/sucursal/save").hasAnyAuthority("SUPERADMIN","ADMIN")
                                //UNIDAD MEDIDA
                                .requestMatchers(HttpMethod.GET, "/UnidadMedida/{id}").hasAnyAuthority("SUPERADMIN","ADMIN","COCINERO")
                                .requestMatchers(HttpMethod.GET, "/UnidadMedida").hasAnyAuthority("SUPERADMIN","ADMIN","COCINERO")
                                .requestMatchers(HttpMethod.GET, "/UnidadMedida/eliminados").hasAnyAuthority("SUPERADMIN","ADMIN","COCINERO")
                                .requestMatchers(HttpMethod.GET, "/UnidadMedida/noEliminados").hasAnyAuthority("SUPERADMIN","ADMIN","COCINERO")
                                .requestMatchers(HttpMethod.POST, "/UnidadMedida").hasAnyAuthority("SUPERADMIN")
                                .requestMatchers(HttpMethod.PUT, "/UnidadMedida/{id}").hasAnyAuthority("SUPERADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/UnidadMedida/{id}").hasAnyAuthority("SUPERADMIN")
                                .requestMatchers(HttpMethod.POST, "/UnidadMedida/activate/{id}").hasAnyAuthority("SUPERADMIN")
                                //USUARIO CLIENTE
                                .requestMatchers(HttpMethod.GET, "/usuarioCliente/{id}").authenticated()
                                .requestMatchers(HttpMethod.GET, "/usuarioCliente").authenticated()
                                .requestMatchers(HttpMethod.GET, "/usuarioCliente/eliminados").authenticated()
                                .requestMatchers(HttpMethod.GET, "/usuarioCliente/noEliminados").authenticated()
                                .requestMatchers(HttpMethod.POST, "/usuarioCliente").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/usuarioCliente/{id}").hasAnyAuthority("SUPERADMIN","CLIENTE","ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/usuarioCliente/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.POST, "/usuarioCliente/activate/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                //USUARIO EMPLEADO
                                .requestMatchers(HttpMethod.GET, "/usuarioEmpleado/{id}").hasAnyAuthority("SUPERADMIN","ADMIN","COCINERO","CAJERO")
                                .requestMatchers(HttpMethod.GET, "/usuarioEmpleado").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/usuarioEmpleado/eliminados").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.GET, "/usuarioEmpleado/noEliminados").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.POST, "/usuarioEmpleado").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/usuarioEmpleado/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/usuarioEmpleado/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")
                                .requestMatchers(HttpMethod.POST, "/usuarioEmpleado/activate/{id}").hasAnyAuthority("SUPERADMIN","ADMIN")

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