package com.example.iticketfinal.service;

import com.example.iticketfinal.dao.entity.ImageEntity;
import com.example.iticketfinal.dao.repository.ImageRepository;
import com.example.iticketfinal.dto.image.ImageDto;
import com.example.iticketfinal.enums.Exceptions;
import com.example.iticketfinal.exceptions.WrongFileNameException;
import com.example.iticketfinal.util.StringUtil;
import io.minio.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    private final MinioClient minioClient;

    public List<ImageDto> saveMultiImages(List<MultipartFile> images, String bucketName) {
        List<ImageDto> imageDtos = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            imageDtos.add(setImageToBucket(images.get(i), bucketName));
        }
        return imageDtos;
    }

    public ImageDto setImageToBucket(MultipartFile image, String bucketName) {
        if (image != null && image.getOriginalFilename() != null) {
            String[] imageDividedName = StringUtil.divideFilename(image.getOriginalFilename());
            if (imageDividedName == null) {
                throw new WrongFileNameException(
                        Exceptions.WRONG_FILE_NAME.toString(),
                        String.format("ActionLog.setImageToBucket.error WrongFileName: %s", image.getOriginalFilename())
                );
            }
            String originalName = imageDividedName[0];
            String extension = imageDividedName[1];

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String formattedDateTime = LocalDateTime.now().format(formatter);

            String cleanedOriginalName = StringUtil.removeSpaces(originalName);
            String imageName = cleanedOriginalName + "_" + formattedDateTime + "." + extension;

            ImageDto imageDto = null;
            try {
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

                String presignedUrl = minioClient.getPresignedObjectUrl(
                        GetPresignedObjectUrlArgs.builder()
                                .bucket(bucketName)
                                .object(imageName)
                                .method(Method.GET)
                                .expiry(10 * 60 * 60)
                                .build()
                );
                imageDto = new ImageDto()
                        .builder()
                        .name(imageName)
                        .bucket(bucketName)
                        .path(presignedUrl)
                        .createdAt(LocalDateTime.now())
                        .build();
//                imageRepository.save(imageEntity);

//                imageDto = imageMapper.mapToDto(imageEntity);

            } catch (Exception e) {
                log.error("ActionLog.setImageToBucket.error Can't file to minio and take imageEntity," +
                        " fileName: {}" +
                        ", bucketName: {}", image.getOriginalFilename(), bucketName);
            }


            return imageDto;
        }
        return null;
    }

    public boolean checkBucketHas(String bucketName) {
        try {
            boolean isExist = minioClient.bucketExists(
                    BucketExistsArgs
                            .builder()
                            .bucket(bucketName)
                            .build()
            );
            return isExist;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteFile(String bucketName, String fileName) {
        try {
            ImageEntity imageEntity = imageRepository.findByNameAndBucket(fileName, bucketName).orElse(null);
            if (imageEntity != null) {
                imageRepository.delete(imageEntity);
            }

            minioClient.removeObject(
                    RemoveObjectArgs
                            .builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            );
        } catch (Exception e) {
            e.printStackTrace();

            log.error("ActionLog.deleteFile fileName: {}, bucketName: {}", fileName, bucketName);
            throw new RuntimeException(e.getMessage());
        }
    }

}
