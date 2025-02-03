package com.weclear.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.weclear.backend.model.Image;
import com.weclear.backend.repository.ImageRepository;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @PostMapping
    public Image uploadImage(@RequestBody Image image) {
        return imageRepository.save(image);
    }

    @GetMapping
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }
}
