package com.example.onlineshopping.service;

import com.example.onlineshopping.domain.Role;
import com.example.onlineshopping.dto.RoleDto;
import com.example.onlineshopping.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleDto findByRole(String role){
        return modelMapper.map(roleRepository.findByRole(role).orElse(null), RoleDto.class);
    }

    public RoleDto save(RoleDto roleDto){
        return modelMapper.map(roleRepository.save(modelMapper.map(roleDto, Role.class)), RoleDto.class);
    }


    public Role save (Role role){
        return roleRepository.save(role);
    }

}
