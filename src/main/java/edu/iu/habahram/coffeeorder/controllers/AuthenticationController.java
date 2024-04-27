package edu.iu.habahram.coffeeorder.controllers;



import edu.iu.habahram.coffeeorder.model.Customer;
import edu.iu.habahram.coffeeorder.repository.CustomerRepository;
import edu.iu.habahram.coffeeorder.security.TokenService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@CrossOrigin
public class AuthenticationController {
    CustomerRepository customerRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(CustomerRepository
                                            customerRepository,
                                    AuthenticationManager authenticationManager,
                                    TokenService tokenService) {
        this.customerRepository = customerRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }
    @PostMapping("/signup")
    public void signup(@RequestBody Customer customer) {
        try {
            customerRepository.save(customer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/signin")
    public String login(@RequestBody Customer customer) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        customer.username(),
                        customer.password()
                )
        );
        return tokenService.generateToken(authentication);
    }
}