package com.jedrzejewski.photogallery.repository;

import com.jedrzejewski.photogallery.entity.Role;
import com.jedrzejewski.photogallery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<User> findAllByRoles(Role role);

    @Query(value = "select * from users join galleries on galleries.user_id = users.id order by galleries.created DESC",
            nativeQuery = true)
    List<User> findAllUsersSortedByGalleryCreated();

    boolean existsUserByEmail(String email);
}
