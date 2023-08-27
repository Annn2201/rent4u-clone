package com.fp.fp.services.Impl;

import com.fp.fp.services.MinIOService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.InputStream;
@Service
@RequiredArgsConstructor
public class MinIOServiceImpl implements MinIOService {
    private final MinioClient minioClient;
    @Value("${minio.bucket.name}")
    private String bucketName;
    @Value("${minio.url}")
    private String minioUrl;

    @Override
    public String putCarImage(String carName, MultipartFile file) {
            String objectName = carName + "-image.png";
            try {
                InputStream inputStream = file.getInputStream();
                minioClient.putObject(PutObjectArgs.builder()
                        .stream(new BufferedInputStream(inputStream), file.getSize(), -1)
                        .bucket(bucketName)
                        .object(objectName)
                        .contentType(file.getContentType())
                        .build());
                return minioUrl + "/" + bucketName + "/" + objectName;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
