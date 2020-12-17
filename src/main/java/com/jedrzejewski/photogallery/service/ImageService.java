package com.jedrzejewski.photogallery.service;

import com.jedrzejewski.photogallery.entity.Gallery;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    void saveImage(MultipartFile file, String email, Gallery gallery) throws IOException;
}
