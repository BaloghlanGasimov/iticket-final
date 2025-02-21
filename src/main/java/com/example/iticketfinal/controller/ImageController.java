package com.example.iticketfinal.controller;

import com.example.iticketfinal.dto.image.ImageDto;
import com.example.iticketfinal.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping(value = "/{bucketName}", consumes = {"multipart/form-data"})
    public ImageDto saveImage(
            @RequestParam("image") MultipartFile image,
            @PathVariable String bucketName
    ) {
        return imageService.setImageToBucket(image, bucketName);
    }

    @PostMapping(value = "/multi/{bucketName}", consumes = {"multipart/form-data"})
    public List<ImageDto> saveMultiImages(
            @RequestParam("image") List<MultipartFile> images,
            @PathVariable String bucketName
    ) {
        return imageService.saveMultiImages(images, bucketName);
    }

    @DeleteMapping(value = "/{bucketName}/{imageName}")
    public void deleteImage(
            @PathVariable String bucketName,
            @PathVariable String imageName
    ) {
        imageService.deleteFile(bucketName, imageName);
    }


}
