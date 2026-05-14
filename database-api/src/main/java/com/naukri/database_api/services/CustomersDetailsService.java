package com.naukri.database_api.services;

import com.naukri.database_api.repositories.UserRepository;
import com.naukri.database_api.repositories.UserDetailsServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomersDetailsService implements UserDetailsService {

    @Autowired
    UserDetailsServiceRepository userDetailsServiceRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailsServiceRepo.loadByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username Not Fount"));
    }
}
