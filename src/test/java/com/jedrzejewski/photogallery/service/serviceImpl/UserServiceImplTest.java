package com.jedrzejewski.photogallery.service.serviceImpl;

import com.jedrzejewski.photogallery.PhotoGalleryApplication;
import com.jedrzejewski.photogallery.entity.Role;
import com.jedrzejewski.photogallery.entity.User;
import com.jedrzejewski.photogallery.repository.UserRepository;
import com.jedrzejewski.photogallery.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PhotoGalleryApplication.class)
class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("test findByEmail")
    void findByEmail() {
        assertAll(
                () -> {
                    assertFalse(userRepository.findAll().isEmpty(), "List should not be empty");
                },
                () -> {
                    List<User> list = userRepository.findAll();
                    String mail = "";
                    for (User current : list) {
                        mail = current.getEmail();
                    }
                    User userByEmail = userService.findByEmail(mail);
                    assertNotNull(userByEmail, "Admin should be found");
                    Set<Role> roles = userByEmail.getRoles();
                    String name = roles.stream().findFirst().get().getName();
                    assertEquals("ROLE_ADMIN", name, "Names should be the same");
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
                    assertEquals(1, userRepository.findAll().size(), "Only Admin should be saved");
                },
                () -> {
                    userService.saveAdmin(user);
                    assertEquals(2, userRepository.findAll().size(), "Two elements should be saved");
                    assertNotNull(userService.findByEmail(user.getEmail()), "User should exist");
                }
        );
    }
}