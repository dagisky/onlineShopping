package com.example.onlineshopping.service;

import com.example.onlineshopping.domain.*;
import com.example.onlineshopping.dto.*;
import com.example.onlineshopping.globalExecption.UserNotFoundException;
import com.example.onlineshopping.repository.CustomerRepository;
import com.example.onlineshopping.repository.RetailerRepository;
import com.example.onlineshopping.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import com.example.onlineshopping.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service @RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final RetailerRepository retailerRepository;
    private final ModelMapper modelMapper;

    public UserDto findById(long id){
        User user = userRepository.findById(id).orElse(null);
        if(user == null) {
            throw new UserNotFoundException("User id-" + id+ " not found");
        }
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto createUser(UserDto userDto){
        return modelMapper.map(userRepository.save(modelMapper.map(userDto, User.class)), UserDto.class);
    }

    public UserCustomerDto createCustomer(UserCustomerDto userDto){
        Customer customer = userDto.getCustomer();
        Role role = roleRepository.findByRole("CUSTOMER").orElse(null);
        userDto.setRole(modelMapper.map(role, Role.class));
        customer.setCart(new ShoppingCart());
        customer = customerRepository.save(customer);
        userDto.setCustomer(customer);
        User user = userRepository.save(modelMapper.map(userDto, User.class));
        return modelMapper.map(user, UserCustomerDto.class);
    }

    public UserRetailerDto createRetailer(UserRetailerDto userDto){
        Retailer retailer = userDto.getRetailer();
        Role role = roleRepository.findByRole("RETAILER").orElse(null);
        userDto.setRole(modelMapper.map(role, Role.class));
        retailer = retailerRepository.save(retailer);
        User user =  userRepository.save(modelMapper.map(userDto, User.class));
        return modelMapper.map(user, UserRetailerDto.class);
    }

    public void deleteUser(UserDto userDto){
        userRepository.delete(modelMapper.map(userDto, User.class));
    }

    public void deleteUserById(long id){
        userRepository.deleteById(id);
    }

    public UserDto updateUser(long id, UserDto userDto){
        Boolean exists = userRepository.existsById(id);
        if(!exists)
            throw new UserNotFoundException("id-"+id);
        User u = modelMapper.map(userDto, User.class);
        u.setId(id);
        return modelMapper.map(userRepository.save(u), UserDto.class);
    }
}
