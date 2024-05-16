package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.ArticuloManufacturadoService;
import com.example.buensaborback.business.services.base.BaseServiceImpl;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.ArticuloManufacturadoDetalle;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

@Service
public class ArticuloManufacturadoServiceImpl extends BaseServiceImpl<ArticuloManufacturado, Long> implements ArticuloManufacturadoService {
    private final RestTemplate restTemplate;

    public ArticuloManufacturadoServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ArticuloManufacturado create(ArticuloManufacturado articuloManufacturado){
        var detalles = articuloManufacturado.getArticuloManufacturadoDetalles();

        var newEntity = baseRepository.save(articuloManufacturado);

        for (ArticuloManufacturadoDetalle detalle : detalles) {
            try {
                System.out.println("hola");
                String url = "http://localhost:8080/ArticuloManufacturadoDetalle";
                System.out.println(detalle);
                restTemplate.postForObject(url, detalle, ArticuloManufacturadoDetalle.class);
                System.out.println("hola2");
            }catch (Exception e){
                // manejo de la excepción
            }
        }
        return newEntity;
    }
}
