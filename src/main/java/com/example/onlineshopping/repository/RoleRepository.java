package com.example.onlineshopping.repository;


import com.example.onlineshopping.domain.Role;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findRoleByRole(String role);
}
