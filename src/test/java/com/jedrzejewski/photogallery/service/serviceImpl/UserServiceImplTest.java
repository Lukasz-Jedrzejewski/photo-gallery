package com.jedrzejewski.photogallery.service.serviceImpl;

import com.jedrzejewski.photogallery.PhotoGalleryApplication;
import com.jedrzejewski.photogallery.entity.User;
import com.jedrzejewski.photogallery.repository.UserRepository;
import com.jedrzejewski.photogallery.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PhotoGalleryApplication.class)
@ActiveProfiles("test")
class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    String email = "test@mail.com";

    @AfterEach
    public void clear() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("test findByEmail")
    void findByEmail() {
        assertAll(
                () -> {
                    assertTrue(userRepository.findAll().isEmpty(), "List should not be empty");
                },
                () -> {
                    userService.saveUser(email);
                    assertNotNull(userService.findByEmail(email), "user should be saved");
                    assertEquals(email, userService.findByEmail(email).getEmail(), "user should be saved");
                }
        );
    }

    @Test
    @DisplayName("test saveAdmin")
    void saveAdmin() {
        User user = new User();
        user.setId((long)2);
        user.setEmail("user@mail.com");
        user.setPassword("aaa");
        assertAll(
                () -> {
                    assertEquals(0, userRepository.findAll().size(), "nothing should be saved");
                },
                () -> {
                    userService.saveAdmin(user);
                    assertEquals(1, userRepository.findAll().size(), "One element should be saved");
                    assertNotNull(userService.findByEmail(user.getEmail()), "User should exist");
                }
        );
    }

    @Test
    @DisplayName("test saveUser")
    void saveUser() {
        userService.saveUser(email);
        assertNotNull(userService.findByEmail(email), "user should be saved");
        assertEquals(email, userService.findByEmail(email).getEmail(), "user should be saved");
    }

    @Test
    @DisplayName("test findAllUsers")
    void findAllUsers() {
        String secondEmail = "sec@mail.com";
        String anotherEmail = "another@mail.com";
        userService.saveUser(email);
        userService.saveUser(secondEmail);
        userService.saveUser(anotherEmail);
        assertEquals(3, userService.findAllUsers().size(), "three elements should be saved");
    }

    @Test
    @DisplayName("test findUserById")
    void findUserById() {
        userService.saveUser(email);
        assertNotNull(userService.findUserById(userService.findByEmail(email).getId()));
    }

    @Test
    @DisplayName("test generatePassword")
    void generatePassword() {
        String pass = userService.generatePassword();
        System.out.println(pass);
        assertNotNull(pass, "String should be generated");
    }

    @Test
    @DisplayName("test editPassword")
    @Transactional
    void editPassword() {
        userService.saveUser(email);
        assertNull(userService.findByEmail(email).getPassword(), "should be null");
        String pass = userService.generatePassword();
        userService.editPassword(userService.findByEmail(email).getId(), pass);
        assertNotNull(userService.findByEmail(email).getPassword(), "password should be changed");
    }
}