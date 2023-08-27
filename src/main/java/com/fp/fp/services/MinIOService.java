package com.fp.fp.services;

import org.springframework.web.multipart.MultipartFile;

public interface MinIOService {
    String putCarImage(String carName, MultipartFile file);
}
