package com.example.springbasicauthexample.service;

import com.example.springbasicauthexample.entity.Role;
import com.example.springbasicauthexample.entity.User;
import com.example.springbasicauthexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User createNewAccount(User user, Role role) {
        user.setRoles(Collections.singletonList(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        role.setUser(user);
        return userRepository.saveAndFlush(user);
    }

    public User findByUserName(String username) {
        return userRepository.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("user with name not found"));
    }
}
