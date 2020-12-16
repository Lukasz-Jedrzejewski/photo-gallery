package com.jedrzejewski.photogallery.service.serviceImpl;

import com.jedrzejewski.photogallery.entity.User;
import com.jedrzejewski.photogallery.repository.RoleRepository;
import com.jedrzejewski.photogallery.repository.UserRepository;
import com.jedrzejewski.photogallery.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.HashSet;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveAdmin(User user) {
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(Collections.singletonList(roleRepository.findByName("ROLE_ADMIN"))));
        userRepository.save(user);
    }

    @Override
    public void saveUser(User user) {
        user.setEnabled(true);
        user.setRoles(new HashSet<>(Collections.singletonList(roleRepository.findByName("ROLE_USER"))));
        userRepository.save(user);
    }
}
