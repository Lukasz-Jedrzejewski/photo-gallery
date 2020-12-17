package com.jedrzejewski.photogallery.service;

import com.jedrzejewski.photogallery.entity.Gallery;
import com.jedrzejewski.photogallery.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    void saveImage(MultipartFile file, String email, Gallery gallery) throws IOException;
}
