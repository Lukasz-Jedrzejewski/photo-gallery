package com.jedrzejewski.photogallery.service.serviceImpl;

import com.jedrzejewski.photogallery.entity.Role;
import com.jedrzejewski.photogallery.repository.RoleRepository;
import com.jedrzejewski.photogallery.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public void saveRole(Role role) {
        boolean existingRole = existRoleByName(role.getName());
        if (!existingRole) {
            roleRepository.save(role);
        }
    }

    @Override
    public boolean existRoleByName(String name) {
        return roleRepository.existsRoleByName(name);
    }
}
