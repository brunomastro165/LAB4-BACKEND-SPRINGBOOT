package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.CloudinaryService;
import com.example.buensaborback.business.services.ImagenSucursalService;
import com.example.buensaborback.domain.dto.ImagenSucursal.ImagenSucursalDto;
import com.example.buensaborback.domain.entities.ImagenSucursal;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.repositories.ImagenSucursalRepository;
import com.example.buensaborback.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Primary
@Service
@Transactional
public class ImagenSucursalServiceImpl extends ImageServiceImpl<ImagenSucursal, UUID> implements ImagenSucursalService {
    @Autowired
    private CloudinaryService cloudinaryService; // Servicio para interactuar con Cloudinary
    @Autowired
    private ImagenSucursalRepository imageRepository;
    @Autowired
    private SucursalRepository sucursalRepository;

    @Override
    protected ImagenSucursal createImageInstance() {
        return new ImagenSucursal();
    }

    public List<ImagenSucursalDto> uploadImagesS(MultipartFile[] files, Long id) {
        List<ImagenSucursalDto> urls = new ArrayList<>();

        try {
            // Iterar sobre cada archivo recibido
            for (MultipartFile file : files) {
                // Verificar si el archivo está vacío
                // Crear una entidad Image y establecer su nombre y URL (subida a Cloudinary)
                ImagenSucursal image = createImageInstance();
                image.setName(file.getOriginalFilename()); // Establecer el nombre del archivo original
                image.setUrl(cloudinaryService.uploadFile(file)); // Subir el archivo a Cloudinary y obtener la URL


                // Guardar la entidad Image en la base de datos
                imageRepository.save(image);
                Sucursal sucursal = sucursalRepository.getById(id);
                sucursal.getImagenes().add(image);
                sucursalRepository.save(sucursal);
                ImagenSucursalDto img = new ImagenSucursalDto();
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
