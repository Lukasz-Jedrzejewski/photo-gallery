package com.jedrzejewski.photogallery.service;

import com.jedrzejewski.photogallery.entity.Gallery;
import com.jedrzejewski.photogallery.entity.Image;

public interface ImageService {
    void saveImage(Image image, Gallery gallery);
}
