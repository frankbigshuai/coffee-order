package edu.iu.habahram.coffeeorder.security;


import edu.iu.habahram.coffeeorder.model.Customer;
import edu.iu.habahram.coffeeorder.repository.CustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component

public class UserDetailsSecurityService implements UserDetailsService {
    CustomerRepository customerRepository;

    public UserDetailsSecurityService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException{
        try{
            Customer customer =
                    customerRepository.findByUsername(username);
            if (customer == null)
            {
                throw new UsernameNotFoundException("");

            }
            return User
                    .withUsername(username)
                    .password(passwordEncoder().encode(customer.password()))
                    .build();
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }


    }}





//changes