package com.jedrzejewski.photogallery.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {
    private final com.jedrzejewski.photogallery.entity.User user;
    public CurrentUser(String email, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       com.jedrzejewski.photogallery.entity.User user) {
        super(email, password, authorities);
        this.user = user;
    }
    public com.jedrzejewski.photogallery.entity.User getUser() {return user;}
}
