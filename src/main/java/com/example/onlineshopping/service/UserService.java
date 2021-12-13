package com.example.onlineshopping.service;

import com.example.onlineshopping.domain.User;
import com.example.onlineshopping.dto.UserAvailabilityRequest;


public interface UserService {

    User getUserByUsername(String username);

    Iterable<User> getAllUsers();

    User getUserById(long id);

    User addUser(User user);

    void deleteUser(long id);

    User isUsernameAvailable(UserAvailabilityRequest userAvailabilityRequest);

}
