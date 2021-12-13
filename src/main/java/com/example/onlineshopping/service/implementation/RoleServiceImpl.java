package com.example.onlineshopping.service.implementation;

import com.example.onlineshopping.domain.Role;
import com.example.onlineshopping.repository.RoleRepository;
import com.example.onlineshopping.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl  implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Iterable<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findRoleByRole(name).get();
    }
}
