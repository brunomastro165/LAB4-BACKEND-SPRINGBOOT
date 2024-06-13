package com.example.buensaborback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BuenSaborBackApplication {
    private static final Logger logger = LoggerFactory.getLogger(BuenSaborBackApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(BuenSaborBackApplication.class, args);
        logger.info("Estoy activo en el main");
        /*
        List<String> entidades = Arrays.asList(
                "Articulo",
                "ArticuloInsumo",
                "ArticuloManufacturado",
                "ArticuloManufacturadoDetalle",
                "categoria",
                "cliente",
                "detallePedido",
                "domicilio",
                "empleado",
                "empresa",
                "factura",
                "localidad",
                "pais",
                "pedido",
                "promocion",
                "pomocionDetalle",
                "sucursal",
                "UnidadMedida",
                "usuarioCliente",
                "usuarioEmpleado"
        );

        for (String entidad : entidades) {
            System.out.println(".requestMatchers(HttpMethod.GET, \"/" + entidad + "/{id}\").hasAnyAuthority(\"ADMIN\", \"COCINERO\")");
            System.out.println(".requestMatchers(HttpMethod.GET, \"/" + entidad + "\").hasAnyAuthority(\"ADMIN\", \"COCINERO\")");
            System.out.println(".requestMatchers(HttpMethod.GET, \"/" + entidad + "/eliminados\").hasAnyAuthority(\"ADMIN\", \"COCINERO\")");
            System.out.println(".requestMatchers(HttpMethod.GET, \"/" + entidad + "/noEliminados\").hasAnyAuthority(\"ADMIN\", \"COCINERO\")");
            System.out.println(".requestMatchers(HttpMethod.POST, \"/" + entidad + "\").hasAnyAuthority(\"ADMIN\", \"COCINERO\")");
            System.out.println(".requestMatchers(HttpMethod.PUT, \"/" + entidad + "/{id}\").hasAnyAuthority(\"ADMIN\", \"COCINERO\")");
            System.out.println(".requestMatchers(HttpMethod.DELETE, \"/" + entidad + "/{id}\").hasAnyAuthority(\"ADMIN\", \"COCINERO\")");
            System.out.println(".requestMatchers(HttpMethod.POST, \"/" + entidad + "/activate/{id}\").hasAnyAuthority(\"ADMIN\", \"COCINERO\")");
        }
*/
    }

}