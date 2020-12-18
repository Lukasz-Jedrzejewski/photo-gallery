package com.jedrzejewski.photogallery.service.serviceImpl;

import com.jedrzejewski.photogallery.PhotoGalleryApplication;
import com.jedrzejewski.photogallery.entity.User;
import com.jedrzejewski.photogallery.repository.GalleryRepository;
import com.jedrzejewski.photogallery.service.GalleryService;
import com.jedrzejewski.photogallery.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PhotoGalleryApplication.class)
@ActiveProfiles("test")
class GalleryServiceImplTest {

    @Autowired
    private GalleryRepository galleryRepository;

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private UserService userService;

    String email = "test@mail.com";
    String name = "wedding session";

    @AfterEach
    public void clear() {
        galleryRepository.deleteAll();
    }

    @Test
    void saveGallery() {
        assertTrue(galleryRepository.findAll().isEmpty(), "The list should be empty");
        userService.saveUser(email);
        galleryService.saveGallery(name, userService.findByEmail(email));
        assertNotNull(galleryRepository.findByUserId(userService.findByEmail(email).getId()));
    }

    @Test
    void findByUserId() {
        galleryService.saveGallery(name, userService.findByEmail(email));
        assertEquals(name, galleryService.findByUserId(userService.findByEmail(email).getId()).getName(),
                "names should be the same");
    }
}