package com.example.onlineshopping.controller;


import com.example.onlineshopping.domain.*;
import com.example.onlineshopping.dto.AuthenticationRequest;
import com.example.onlineshopping.dto.AuthenticationResponse;
import com.example.onlineshopping.dto.UserRegistrationRequest;
import com.example.onlineshopping.security.CustomUserDetailsService;
import com.example.onlineshopping.service.BuyerService;
import com.example.onlineshopping.service.RoleService;
import com.example.onlineshopping.service.SellerService;
import com.example.onlineshopping.util.JwtUtil;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@CrossOrigin("http://localhost:3000")
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = customUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        if (userRegistrationRequest.isBuyer()) {
            Buyer buyer = new Buyer();
            Set<Order> orders = new HashSet<>();
            Set<Address> addresses = new HashSet<>();
            Set<Review> reviews = new HashSet<>();
            buyer.setUsername(userRegistrationRequest.getUsername());
            buyer.setPassword(new BCryptPasswordEncoder().encode(userRegistrationRequest.getPassword()));
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getRoleByName("ROLE_BUYER"));
            buyer.setRoles(roles);
            buyer.setFullName(userRegistrationRequest.getFullName());
            buyer.setBalance(0);
            buyer.setOrders(orders);
            buyer.setAddresses(addresses);
            buyer.setReviews(reviews);
            Buyer savedBuyer = buyerService.addBuyer(buyer);
            System.out.println("Buyer Created!");
            return ResponseEntity.ok(savedBuyer);
        }
        if (userRegistrationRequest.isSeller()) {
            Seller seller = new Seller();
            Set<Order> orders = new HashSet<>();
            Set<Product> products = new HashSet<>();
            seller.setUsername(userRegistrationRequest.getUsername());
            seller.setPassword(new BCryptPasswordEncoder().encode(userRegistrationRequest.getPassword()));
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getRoleByName("ROLE_SELLER"));
            seller.setRoles(roles);
            seller.setProducts(products);
            seller.setApproved(false);
            Seller savedSeller = sellerService.addSeller(seller);
            System.out.println("Seller Created!");
            return ResponseEntity.ok(savedSeller);
        }
        return ResponseEntity.ok("User Created");
    }

    @GetMapping(value = "/refreshtoken")
    public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {

        DefaultClaims claims = (DefaultClaims) request.getAttribute("claims");
        Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
        String token = jwtTokenUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
    }
}
