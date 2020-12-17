package com.jedrzejewski.photogallery.service.serviceImpl;

import com.jedrzejewski.photogallery.entity.Gallery;
import com.jedrzejewski.photogallery.entity.User;
import com.jedrzejewski.photogallery.repository.GalleryRepository;
import com.jedrzejewski.photogallery.service.GalleryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepository galleryRepository;

    @Override
    public void saveGallery(String name, User user) {
        Gallery gallery = new Gallery();
        gallery.setName(name);
        gallery.setUser(user);
        galleryRepository.save(gallery);
    }

    @Override
    public Gallery findByUserId(long id) {
        return galleryRepository.findByUserId(id);
    }
}
