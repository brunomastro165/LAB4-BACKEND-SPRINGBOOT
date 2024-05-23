package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.CloudinaryService;
import com.example.buensaborback.business.services.ImagenClienteService;
import com.example.buensaborback.domain.entities.ImagenArticulo;
import com.example.buensaborback.domain.entities.ImagenCliente;
import com.example.buensaborback.repositories.ImagenArticuloRepository;
import com.example.buensaborback.repositories.ImagenClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@Primary
public class ImagenClienteServiceImpl extends ImageServiceImpl<ImagenCliente, UUID> implements ImagenClienteService {
    @Override
    protected ImagenCliente createImageInstance() {
        return new ImagenCliente();
    }
    @Autowired
    private CloudinaryService cloudinaryService; // Servicio para interactuar con Cloudinary
    @Autowired
    private ImagenClienteRepository imageRepository;/*
    public ResponseEntity<String> uploadImages(MultipartFile[] files,Long id) {
        List<String> urls = new ArrayList<>();

        try {
            // Iterar sobre cada archivo recibido
            for (MultipartFile file : files) {
                // Verificar si el archivo está vacío


                // Crear una entidad Image y establecer su nombre y URL (subida a Cloudinary)
                ImagenCliente image = createImageInstance();
                image.setName(file.getOriginalFilename()); // Establecer el nombre del archivo original
                image.setUrl(cloudinaryService.uploadFile(file)); // Subir el archivo a Cloudinary y obtener la URL
                image.setArticulo(articuloRepository.getById(id));


                // Guardar la entidad Image en la base de datos
                imageRepository.save(image);

                // Agregar la URL de la imagen a la lista de URLs subidas
                urls.add(image.getUrl());
            }

            // Convertir la lista de URLs a un objeto JSON y devolver como ResponseEntity con estado OK (200)
            return new ResponseEntity<>("{\"status\":\"OK\", \"urls\":" + urls + "}", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            // Devolver un error (400) si ocurre alguna excepción durante el proceso de subida
            return new ResponseEntity<>("{\"status\":\"ERROR\", \"message\":\"" + e.getMessage() + "\"}", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> getImageById(Long id) {
        try {
            // Consultar la imagen desde la base de datos
            Optional<ImagenArticulo> optionalImage = imageRepository.findByIdArticulo(id);

            // Verificar si la imagen existe
            if (optionalImage.isPresent()) {
                ImagenArticulo image = optionalImage.get();

                // Convertir la imagen en un mapa de atributos para devolver como JSON
                Map<String, Object> imageMap = new HashMap<>();
                imageMap.put("id", image.getId());
                imageMap.put("name", image.getName());
                imageMap.put("url", image.getUrl());

                // Devolver la imagen como ResponseEntity con estado OK (200)
                return ResponseEntity.ok(imageMap);
            } else {
                // Devolver un error (404) si la imagen no se encuentra
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Devolver un error interno del servidor (500) si ocurre alguna excepción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        */
}
