package com.training.springbootbuyitem.service;

import com.training.springbootbuyitem.entity.model.Role;
import com.training.springbootbuyitem.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

}
