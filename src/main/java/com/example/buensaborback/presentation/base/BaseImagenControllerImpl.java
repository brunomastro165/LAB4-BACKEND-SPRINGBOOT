package com.example.buensaborback.presentation.base;

import com.example.buensaborback.business.services.ImageService;
import com.example.buensaborback.domain.entities.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public abstract class BaseImagenControllerImpl<E extends Image, ID extends Serializable> implements BaseImagenController {
    @Autowired
    private ImageService<E,ID> imageService; // Inyección de dependencia del servicio ImageService

    // Método POST para subir imágenes
    @PostMapping("/uploads")
    public ResponseEntity<String> uploadImages(
            @RequestParam(value = "uploads", required = true) MultipartFile[] files) {
        try {

            return imageService.uploadImages(files,null); // Llama al método del servicio para subir imágenes
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Manejo básico de errores, se puede mejorar para devolver una respuesta más específica
        }
    }

    // Método POST para eliminar imágenes por su publicId y UUID
    @PostMapping("/deleteImg")
    public ResponseEntity<String> deleteById(
            @RequestParam(value = "publicId", required = true) String publicId,
            @RequestParam(value = "uuid", required = true) String uuidString) {
        try {
            UUID uuid = UUID.fromString(uuidString); // Convierte la cadena UUID en un objeto UUID
            return imageService.deleteImage(publicId, uuid); // Llama al método del servicio para eliminar la imagen
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Invalid UUID format"); // Respuesta HTTP 400 si el UUID no es válido
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred"); // Respuesta HTTP 500 si ocurre un error inesperado
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>>  getById(@PathVariable UUID id) {
        try {
            return imageService.getImageById(id); // Llama al método del servicio para obtener todas las imágenes
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Manejo básico de errores, se puede mejorar para devolver una respuesta más específica
        }
    }

    // Método GET para obtener todas las imágenes almacenadas
    @GetMapping("")
    public ResponseEntity<List<Map<String, Object>>> getAll() {
        try {
            return imageService.getAllImages(); // Llama al método del servicio para obtener todas las imágenes
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Manejo básico de errores, se puede mejorar para devolver una respuesta más específica
        }
    }
}
