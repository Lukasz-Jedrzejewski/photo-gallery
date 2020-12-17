package com.jedrzejewski.photogallery.service;

import com.jedrzejewski.photogallery.entity.User;

import java.util.List;

public interface UserService {
    User findByEmail(String email);
    void saveAdmin(User user);
    void saveUser(String email);
    List<User> findAllUsers();
    User findUserById(long id);
}
