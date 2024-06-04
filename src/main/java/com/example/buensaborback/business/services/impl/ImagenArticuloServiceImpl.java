package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.CloudinaryService;
import com.example.buensaborback.business.services.ImagenArticuloService;
import com.example.buensaborback.domain.dto.ImagenArticuloDto.ImagenArticuloDto;
import com.example.buensaborback.domain.entities.ImagenArticulo;
import com.example.buensaborback.repositories.ArticuloRepository;
import com.example.buensaborback.repositories.ImagenArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@Primary
@Transactional
public class ImagenArticuloServiceImpl extends ImageServiceImpl<ImagenArticulo, UUID> implements ImagenArticuloService {
    @Autowired
    private CloudinaryService cloudinaryService; // Servicio para interactuar con Cloudinary
    @Autowired
    private ImagenArticuloRepository imageRepository;
    @Autowired
    private ArticuloRepository articuloRepository;

    @Override
    protected ImagenArticulo createImageInstance() {
        return new ImagenArticulo();
    }

    public List<ImagenArticuloDto> uploadImagesA(MultipartFile[] files, Long id) {
        List<ImagenArticuloDto> urls = new ArrayList<>();

        try {
            // Iterar sobre cada archivo recibido
            for (MultipartFile file : files) {
                // Verificar si el archivo está vacío
                // Crear una entidad Image y establecer su nombre y URL (subida a Cloudinary)
                ImagenArticulo image = createImageInstance();
                image.setName(file.getOriginalFilename()); // Establecer el nombre del archivo original
                image.setUrl(cloudinaryService.uploadFile(file)); // Subir el archivo a Cloudinary y obtener la URL
                image.setArticulo(articuloRepository.getById(id));


                // Guardar la entidad Image en la base de datos
                imageRepository.save(image);
                ImagenArticuloDto img = new ImagenArticuloDto();
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
    }
}
