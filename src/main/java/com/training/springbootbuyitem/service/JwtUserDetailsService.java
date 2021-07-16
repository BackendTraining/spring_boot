package com.training.springbootbuyitem.service;

import com.training.springbootbuyitem.repository.UserRepository;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Example<com.training.springbootbuyitem.entity.model.User> userExample = Example.of(com.training.springbootbuyitem.entity.model.User.builder().email(username).build());
        com.training.springbootbuyitem.entity.model.User user = userRepository
            .findOne(userExample)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with e-mail: " + username));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(user.getPassword());
        return new User(user.getEmail(), password, new ArrayList<>());
    }
}