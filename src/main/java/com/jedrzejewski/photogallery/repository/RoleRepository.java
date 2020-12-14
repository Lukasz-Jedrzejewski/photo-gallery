package com.jedrzejewski.photogallery.repository;

import com.jedrzejewski.photogallery.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
    boolean existsRoleByName(String name);
}
