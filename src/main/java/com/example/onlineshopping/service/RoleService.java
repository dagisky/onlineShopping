package com.example.onlineshopping.service;

import com.example.onlineshopping.domain.Role;
import com.example.onlineshopping.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

public interface RoleService {

    Iterable<Role> getAllRoles();

    Role getRoleByName(String name);

}
