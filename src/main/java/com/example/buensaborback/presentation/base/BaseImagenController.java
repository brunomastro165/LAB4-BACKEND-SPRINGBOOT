package com.example.buensaborback.presentation.base;

import com.example.buensaborback.domain.entities.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseImagenController<E extends Image, ID extends Serializable> {
    ResponseEntity<String> uploadImages(
            @RequestParam(value = "uploads", required = true) MultipartFile[] files, Long id);

    ResponseEntity<String> deleteById(
            @RequestParam(value = "publicId", required = true) String publicId,
            @RequestParam(value = "uuid", required = true) String uuidString);

    ResponseEntity<List<Map<String, Object>>> getAll();

    ResponseEntity<Map<String, Object>> getById(@PathVariable Long id);
}
