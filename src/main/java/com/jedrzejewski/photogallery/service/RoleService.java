package com.jedrzejewski.photogallery.service;

import com.jedrzejewski.photogallery.entity.Role;

public interface RoleService {
    void saveRole(Role role);
    boolean existRoleByName(String name);
}
