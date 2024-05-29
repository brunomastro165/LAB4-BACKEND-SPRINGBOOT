package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.CloudinaryService;
import com.example.buensaborback.business.services.ImagenEmpresaService;
import com.example.buensaborback.domain.dto.ImagenEmpresa.ImagenEmpresaDto;
import com.example.buensaborback.domain.entities.ImagenEmpresa;
import com.example.buensaborback.repositories.EmpresaRepository;
import com.example.buensaborback.repositories.ImagenEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Primary
public class ImagenEmpresaServiceImpl extends ImageServiceImpl<ImagenEmpresa, UUID> implements ImagenEmpresaService {
    @Autowired
    private CloudinaryService cloudinaryService; // Servicio para interactuar con Cloudinary
    @Autowired
    private ImagenEmpresaRepository imageRepository;
    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    protected ImagenEmpresa createImageInstance() {
        return new ImagenEmpresa();
    }

    public List<ImagenEmpresaDto> uploadImagesE(MultipartFile[] files, Long id) {
        List<ImagenEmpresaDto> urls = new ArrayList<>();

        try {
            // Iterar sobre cada archivo recibido
            for (MultipartFile file : files) {
                // Verificar si el archivo está vacío
                // Crear una entidad Image y establecer su nombre y URL (subida a Cloudinary)
                ImagenEmpresa image = createImageInstance();
                image.setName(file.getOriginalFilename()); // Establecer el nombre del archivo original
                image.setUrl(cloudinaryService.uploadFile(file)); // Subir el archivo a Cloudinary y obtener la URL
                image.setEmpresa(empresaRepository.getById(id));


                // Guardar la entidad Image en la base de datos
                imageRepository.save(image);
                ImagenEmpresaDto img = new ImagenEmpresaDto();
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
