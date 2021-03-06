package com.jedrzejewski.photogallery.service;

import com.jedrzejewski.photogallery.entity.Gallery;
import com.jedrzejewski.photogallery.entity.User;

public interface GalleryService {
    void saveGallery(String name, User user);
    Gallery findByUserId(long id);
}
