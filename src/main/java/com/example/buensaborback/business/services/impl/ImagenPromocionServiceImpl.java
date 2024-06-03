package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.CloudinaryService;
import com.example.buensaborback.business.services.ImagenPromocionService;
import com.example.buensaborback.domain.dto.ImagenPromocion.ImagenPromocionDto;
import com.example.buensaborback.domain.entities.ImagenPromocion;
import com.example.buensaborback.domain.entities.Promocion;
import com.example.buensaborback.repositories.ImagenPromocionRepository;
import com.example.buensaborback.repositories.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Primary
@Service
public class ImagenPromocionServiceImpl extends ImageServiceImpl<ImagenPromocion, UUID> implements ImagenPromocionService {
    @Autowired
    private CloudinaryService cloudinaryService; // Servicio para interactuar con Cloudinary
    @Autowired
    private ImagenPromocionRepository imageRepository;
    @Autowired
    private PromocionRepository promocionRepository;

    @Override
    protected ImagenPromocion createImageInstance() {
        return new ImagenPromocion();
    }

    public List<ImagenPromocionDto> uploadImagesP(MultipartFile[] files, Long id) {
        List<ImagenPromocionDto> urls = new ArrayList<>();

        try {
            // Iterar sobre cada archivo recibido
            for (MultipartFile file : files) {
                // Verificar si el archivo está vacío
                // Crear una entidad Image y establecer su nombre y URL (subida a Cloudinary)
                ImagenPromocion image = createImageInstance();
                image.setName(file.getOriginalFilename()); // Establecer el nombre del archivo original
                image.setUrl(cloudinaryService.uploadFile(file)); // Subir el archivo a Cloudinary y obtener la URL


                // Guardar la entidad Image en la base de datos
                imageRepository.save(image);
                Promocion promocion = promocionRepository.getById(id);
                promocion.getImagenes().add(image);
                promocionRepository.save(promocion);
                ImagenPromocionDto img = new ImagenPromocionDto();
                img.setName(image.getName());
                img.setUrl(image.getUrl());
                // Agregar la URL de la imagen a la lista de URLs subidas
                urls.add(img);
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
