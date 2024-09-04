package com.example.iticketfinal.service;

import com.example.iticketfinal.dao.entity.ImageEntity;
import com.example.iticketfinal.dao.repository.ImageRepository;
import com.example.iticketfinal.util.StringUtil;
import io.minio.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    private final MinioClient minioClient;
    private final StringUtil stringUtil;

    public ImageEntity setImageToBucket(MultipartFile image,String bucketName) throws Exception {
        if (image != null && image.getOriginalFilename() != null) {
            String[] imageDividedName = stringUtil.divideFilename(image.getOriginalFilename());
            if(imageDividedName == null){
                throw new Exception();
            }
            String originalName = imageDividedName[0];
            String extension = imageDividedName[1];

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String formattedDateTime = LocalDateTime.now().format(formatter);

            String cleanedOriginalName = stringUtil.removeSpaces(originalName);
            String imageName = cleanedOriginalName + "_" + formattedDateTime + "." + extension;



            if (!checkBucketHas(bucketName)) {
            minioClient.makeBucket(
                    MakeBucketArgs
                            .builder()
                            .bucket(bucketName)
                            .build());
        }

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(imageName)
                        .stream(
                                image.getInputStream(),
                                image.getSize(),
                                -1
                        )
                        .build()
        );

        System.out.println("burda2");

        String presignedUrl = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .bucket(bucketName)
                        .object(imageName)
                        .method(Method.GET)
                        .expiry(10 * 60 * 60)
                        .build()
        );
        ImageEntity imageEntity = new ImageEntity()
                        .builder()
                        .name(imageName)
                        .path(presignedUrl)
                .build();
        imageRepository.save(imageEntity);
        return imageEntity;
    }
        return null;
    }

    public boolean checkBucketHas(String bucketName){
        try {
            boolean isExist = minioClient.bucketExists(
                    BucketExistsArgs
                            .builder()
                            .bucket(bucketName)
                            .build()
            );
            return isExist;
        }catch (Exception e){
            return false;
        }

    }

}
