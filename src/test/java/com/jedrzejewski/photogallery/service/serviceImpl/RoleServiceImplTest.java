package com.jedrzejewski.photogallery.service.serviceImpl;

import com.jedrzejewski.photogallery.PhotoGalleryApplication;
import com.jedrzejewski.photogallery.entity.Role;
import com.jedrzejewski.photogallery.repository.RoleRepository;
import com.jedrzejewski.photogallery.service.RoleService;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PhotoGalleryApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    Role role = new Role((long) 1, "ROLE_TEST");

    @Test
    @DisplayName("test saveRole")
    void saveRole() {
        assertAll(
                () -> {
                    assertFalse(roleRepository.findAll().isEmpty(), "Nothing should be saved");
                    assertEquals(2, roleRepository.findAll().size(), "admin and user roles should be saved");
                },
                () -> {
                    roleService.saveRole(role);
                    assertNotNull(roleRepository.getOne(role.getId()), "should be saved");
                    assertEquals(role.getName(), roleRepository.findByName(role.getName()).getName(),
                            "ROLE_TEST should be found");
                }
        );
    }

    @Test
    @DisplayName("test existRoleByName")
    void existRoleByName() {
        roleService.saveRole(role);
        assertTrue(roleService.existRoleByName(role.getName()), "ROLE_TEST should be found");
    }
}