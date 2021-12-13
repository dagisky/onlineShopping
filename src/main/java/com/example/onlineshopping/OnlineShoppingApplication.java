package com.example.onlineshopping;

import com.example.onlineshopping.domain.Role;
import com.example.onlineshopping.service.RoleService;
import com.example.onlineshopping.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class OnlineShoppingApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineShoppingApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {return new ModelMapper();}

}
