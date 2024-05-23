package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.CloudinaryService;
import com.example.buensaborback.business.services.ImagenArticuloService;
import com.example.buensaborback.domain.entities.ImagenArticulo;
import com.example.buensaborback.repositories.ArticuloRepository;
import com.example.buensaborback.repositories.ImageRepository;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Primary
public class ImagenArticuloServiceImpl extends ImageServiceImpl<ImagenArticulo, UUID> implements ImagenArticuloService {
    @Autowired
    private CloudinaryService cloudinaryService; // Servicio para interactuar con Cloudinary
    @Autowired
    private ImageRepository<ImagenArticulo, UUID> imageRepository;
    @Autowired
    private ArticuloRepository articuloRepository;
    @Override
    protected ImagenArticulo createImageInstance() {
        return new ImagenArticulo();
    }

    public List<String> uploadImages(MultipartFile[] files) {
        List<String> urls = new ArrayList<>();

        try {
            // Iterar sobre cada archivo recibido
            for (MultipartFile file : files) {
                // Verificar si el archivo está vacío


                // Crear una entidad Image y establecer su nombre y URL (subida a Cloudinary)
                ImagenArticulo image = createImageInstance();
                image.setName(file.getOriginalFilename()); // Establecer el nombre del archivo original
                image.setUrl(cloudinaryService.uploadFile(file)); // Subir el archivo a Cloudinary y obtener la URL





                // Guardar la entidad Image en la base de datos
                imageRepository.save(image);

                // Agregar la URL de la imagen a la lista de URLs subidas
                urls.add(image.getUrl());
            }

            // Convertir la lista de URLs a un objeto JSON y devolver como ResponseEntity con estado OK (200)
            return urls;

        } catch (Exception e) {
            e.printStackTrace();
            // Devolver un error (400) si ocurre alguna excepción durante el proceso de subida
            return null;
        }
    }
}
