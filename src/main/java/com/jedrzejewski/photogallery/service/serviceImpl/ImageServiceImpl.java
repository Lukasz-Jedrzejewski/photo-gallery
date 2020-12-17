package com.jedrzejewski.photogallery.service.serviceImpl;

import com.jedrzejewski.photogallery.entity.Gallery;
import com.jedrzejewski.photogallery.entity.Image;
import com.jedrzejewski.photogallery.repository.ImageRepository;
import com.jedrzejewski.photogallery.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public void saveImage(Image image, Gallery gallery) {
        imageRepository.save(image);
    }
}
