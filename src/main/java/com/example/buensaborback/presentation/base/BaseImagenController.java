package com.example.buensaborback.presentation.base;

import com.example.buensaborback.domain.entities.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseImagenController <E extends Image, ID extends Serializable>{
    public ResponseEntity<String> uploadImages(
            @RequestParam(value = "uploads", required = true) MultipartFile[] files);

    public ResponseEntity<String> deleteById(
            @RequestParam(value = "publicId", required = true) String publicId,
            @RequestParam(value = "uuid", required = true) String uuidString);

    public ResponseEntity<List<Map<String, Object>>> getAll();
}
