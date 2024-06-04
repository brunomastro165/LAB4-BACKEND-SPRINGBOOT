package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.CloudinaryService;
import com.example.buensaborback.business.services.ImagenEmpleadoService;
import com.example.buensaborback.domain.dto.ImagenEmpleado.ImagenEmpleadoDto;
import com.example.buensaborback.domain.entities.Empleado;
import com.example.buensaborback.domain.entities.ImagenEmpleado;
import com.example.buensaborback.repositories.EmpleadoRepository;
import com.example.buensaborback.repositories.ImagenEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Primary
@Service
@Transactional
public class ImagenEmpleadoServiceImpl extends ImageServiceImpl<ImagenEmpleado, UUID> implements ImagenEmpleadoService {
    @Autowired
    private CloudinaryService cloudinaryService; // Servicio para interactuar con Cloudinary
    @Autowired
    private ImagenEmpleadoRepository imageRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    protected ImagenEmpleado createImageInstance() {
        return new ImagenEmpleado();
    }

    public ImagenEmpleadoDto uploadImagesE(MultipartFile file, Long id) {
        ImagenEmpleadoDto url;

        try {

            // Verificar si el archivo está vacío
            // Crear una entidad Image y establecer su nombre y URL (subida a Cloudinary)
            ImagenEmpleado image = createImageInstance();
            image.setName(file.getOriginalFilename()); // Establecer el nombre del archivo original
            image.setUrl(cloudinaryService.uploadFile(file)); // Subir el archivo a Cloudinary y obtener la URL

            // Guardar la entidad Image en la base de datos
            imageRepository.save(image);
            Empleado empleado = empleadoRepository.getById(id);
            empleado.setImagenEmpleado(image);
            empleadoRepository.save(empleado);

            ImagenEmpleadoDto img = new ImagenEmpleadoDto();
            img.setName(image.getName());
            img.setUrl(image.getUrl());

            url = img;


            return url;

        } catch (Exception e) {
            e.printStackTrace();
            // Devolver un error (400) si ocurre alguna excepción durante el proceso de subida
            return null;
        }
    }
}
