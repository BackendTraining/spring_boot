package com.training.springbootbuyitem.service;

import com.training.springbootbuyitem.entity.model.Role;
import com.training.springbootbuyitem.entity.model.User;
import com.training.springbootbuyitem.enums.EnumEntity;
import com.training.springbootbuyitem.error.EntityNotFoundException;
import com.training.springbootbuyitem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public User get(Long userUid) {
        return userRepository.findById(userUid)
            .orElseThrow(() -> new EntityNotFoundException(EnumEntity.USER.name(), userUid));
    }

    @Override
    public List<User> get(List<Long> id) {
        return new ArrayList<>();
    }

    @Override
    public User save(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleService.findByName("USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        user.setRoles(roleSet);

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
        if (StringUtils.hasText(user.getEmail())) {
            persistedUser.setEmail(user.getEmail());
        }
        if (StringUtils.hasText(user.getPassword())) {
            persistedUser.setPassword(user.getPassword());
        }

        return userRepository.save(persistedUser);
    }

    @Override
    public void deleteByUser(User user) {
        userRepository.delete(user);
    }
}
