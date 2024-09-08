package com.example.iticketfinal.controller;

import com.example.iticketfinal.dao.entity.ImageEntity;
import com.example.iticketfinal.dao.repository.ImageRepository;
import com.example.iticketfinal.dto.image.ImageDto;
import com.example.iticketfinal.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping(value = "/{bucketName}",consumes = { "multipart/form-data" })
    public ImageDto saveImage(
            @RequestParam("image") MultipartFile image,
            @PathVariable String bucketName
    ){
        return imageService.setImageToBucket(image,bucketName);
    }

    @DeleteMapping(value = "/{bucketName}/{imageName}")
    public void deleteImage(
            @PathVariable String bucketName,
            @PathVariable String imageName
    ){
        imageService.deleteFile(bucketName,imageName);
    }


}
