package com.jedrzejewski.photogallery.fixture;

import com.jedrzejewski.photogallery.entity.Role;
import com.jedrzejewski.photogallery.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Init {

    private final RoleService roleService;

    public void initRoles() {
        Role roleAdmin = new Role((long) 1, "ROLE_ADMIN");
        Role roleUser = new Role((long) 2, "ROLE_USER");
        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);
    }
}
