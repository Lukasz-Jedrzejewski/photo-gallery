package com.jedrzejewski.photogallery.fixture;

import com.jedrzejewski.photogallery.entity.Role;
import com.jedrzejewski.photogallery.entity.User;
import com.jedrzejewski.photogallery.repository.UserRepository;
import com.jedrzejewski.photogallery.service.RoleService;
import com.jedrzejewski.photogallery.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Init {

    @Value("${spring.mail.username}")
    private String adminEmail;
    @Value("${spring.mail.password}")
    private String adminPassword;

    private final RoleService roleService;
    private final UserService userService;
    private final UserRepository userRepository;

    public Init(RoleService roleService, UserService userService, UserRepository userRepository) {
        this.roleService = roleService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public void initRoles() {
        Role roleAdmin = new Role((long) 1, "ROLE_ADMIN");
        Role roleUser = new Role((long) 2, "ROLE_USER");
        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);
    }

    public void initAdmin() {
        User admin = new User();
        admin.setId(1L);
        admin.setEmail(adminEmail);
        admin.setPassword(adminPassword);
        if (!userRepository.existsUserByEmail(admin.getEmail())) {
            userService.saveAdmin(admin);
        }
    }
}
