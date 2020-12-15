package com.jedrzejewski.photogallery.service;

import com.jedrzejewski.photogallery.entity.User;

public interface UserService {
    User findByEmail(String email);
    void saveAdmin(User user);
}
