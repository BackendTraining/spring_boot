package com.training.springbootbuyitem.service;

import com.training.springbootbuyitem.entity.model.User;
import com.training.springbootbuyitem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public User get(Long userUid) {
        return userRepository.findById(userUid).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<User> get(List<Long> id) {
        return null;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(Long userUid) {
        userRepository.deleteById(userUid);
    }

    @Override
    public User update(User user) {
        User persistedUser = get(user.getUserUid());
        if (StringUtils.hasText(user.getFirstName())) {
            persistedUser.setFirstName(user.getFirstName());
        }
        if (StringUtils.hasText(user.getLastName())) {
            persistedUser.setLastName(user.getLastName());
        }
        return userRepository.save(persistedUser);
    }

    @Override
    public void deleteByUser(User user) {
        userRepository.delete(user);
    }
}
