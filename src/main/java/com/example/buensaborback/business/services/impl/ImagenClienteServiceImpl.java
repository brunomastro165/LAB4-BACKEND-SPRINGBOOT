package com.example.buensaborback.business.services.impl;

import com.example.buensaborback.business.services.CloudinaryService;
import com.example.buensaborback.business.services.ImagenClienteService;
import com.example.buensaborback.domain.dto.ImagenCliente.ImagenClienteDto;
import com.example.buensaborback.domain.entities.Cliente;
import com.example.buensaborback.domain.entities.ImagenCliente;
import com.example.buensaborback.repositories.ClienteRepository;
import com.example.buensaborback.repositories.ImagenClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@Primary
public class ImagenClienteServiceImpl extends ImageServiceImpl<ImagenCliente, UUID> implements ImagenClienteService {
    @Autowired
    private CloudinaryService cloudinaryService; // Servicio para interactuar con Cloudinary
    @Autowired
    private ImagenClienteRepository imageRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    protected ImagenCliente createImageInstance() {
        return new ImagenCliente();
    }

    public ImagenClienteDto uploadImagesC(MultipartFile file, Long id) {
        ImagenClienteDto url;

        try {

            // Verificar si el archivo está vacío
            // Crear una entidad Image y establecer su nombre y URL (subida a Cloudinary)
            ImagenCliente image = createImageInstance();
            image.setName(file.getOriginalFilename()); // Establecer el nombre del archivo original
            image.setUrl(cloudinaryService.uploadFile(file)); // Subir el archivo a Cloudinary y obtener la URL

            // Guardar la entidad Image en la base de datos
            imageRepository.save(image);
            Cliente cliente = clienteRepository.getById(id);
            cliente.setImagenCliente(image);
            clienteRepository.save(cliente);

            ImagenClienteDto img = new ImagenClienteDto();
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
