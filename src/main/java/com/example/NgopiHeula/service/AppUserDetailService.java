package com.example.NgopiHeula.service;

import com.example.NgopiHeula.config.ApplicationUserDetails;
import com.example.NgopiHeula.model.entity.Account;
import com.example.NgopiHeula.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
        return new ApplicationUserDetails(account);
    }
}
