package com.jedrzejewski.photogallery.service.serviceImpl;

import com.jedrzejewski.photogallery.entity.Role;
import com.jedrzejewski.photogallery.entity.User;
import com.jedrzejewski.photogallery.repository.RoleRepository;
import com.jedrzejewski.photogallery.repository.UserRepository;
import com.jedrzejewski.photogallery.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

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
    public void saveUser(String email) {
        User user = new User();
        user.setEmail(email);
        user.setEnabled(true);
        user.setRoles(new HashSet<>(Collections.singletonList(roleRepository.findByName("ROLE_USER"))));
        userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        Role role = roleRepository.findByName("ROLE_USER");
        return userRepository.findAllByRoles(role);
    }

    @Override
    public User findUserById(long id) {
        return userRepository.getOne(id);
    }

    @Override
    public String generatePassword() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    @Override
    public void editPassword(long id, String password) {
        User userFromDb = findUserById(id);
        userFromDb.setPassword(passwordEncoder.encode(password));
        userRepository.save(userFromDb);
    }
}
