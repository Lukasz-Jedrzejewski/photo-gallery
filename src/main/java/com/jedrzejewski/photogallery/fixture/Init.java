package com.jedrzejewski.photogallery.fixture;

import com.jedrzejewski.photogallery.entity.Role;
import com.jedrzejewski.photogallery.entity.User;
import com.jedrzejewski.photogallery.repository.UserRepository;
import com.jedrzejewski.photogallery.service.RoleService;
import com.jedrzejewski.photogallery.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Init {

    private final RoleService roleService;
    private final UserService userService;
    private final UserRepository userRepository;

    public void initRoles() {
        Role roleAdmin = new Role((long) 1, "ROLE_ADMIN");
        Role roleUser = new Role((long) 2, "ROLE_USER");
        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);
    }

    public void initAdmin() {
        User admin = new User();
        admin.setId(1L);
        admin.setEmail("admin@mail.com");
        admin.setPassword("aaaAAA12@");
        if (!userRepository.existsUserByEmail(admin.getEmail())) {
            userService.saveAdmin(admin);
        }
    }
}
