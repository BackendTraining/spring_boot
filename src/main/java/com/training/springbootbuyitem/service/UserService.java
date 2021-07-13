package com.training.springbootbuyitem.service;

import com.training.springbootbuyitem.entity.model.User;
import com.training.springbootbuyitem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long userUid) {
        return userRepository.findById(userUid).orElseThrow(RuntimeException::new);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void deleteById(Long userUid) {
        userRepository.deleteById(userUid);
    }

}
