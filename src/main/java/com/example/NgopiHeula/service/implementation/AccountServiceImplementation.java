package com.example.NgopiHeula.service.implementation;

import com.example.NgopiHeula.model.dto.account.AccountUpsertDTO;
import com.example.NgopiHeula.model.dto.account.AccountViewDTO;
import com.example.NgopiHeula.model.entity.Account;
import com.example.NgopiHeula.repository.AccountRepository;
import com.example.NgopiHeula.service.abstraction.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImplementation implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String getAccountRole(String username) {
        Optional<Account> nullableEntity = accountRepository.findById(username);
        Account account = nullableEntity.get();
        return account.getRole();
    }

    @Override
    public List<AccountViewDTO> getAllAccount(String username) {
        return accountRepository.getAllAccount(username);
    }

    @Override
    public boolean checkExistingAccount(String username) {
        Long totalUser = accountRepository.countByUsername(username);
        return (totalUser > 0) ? true : false;
    }

    @Override
    public void register(AccountUpsertDTO dto) {
        Account account = new Account(dto.getUsername(), passwordEncoder.encode(dto.getPassword()), dto.getRole(), LocalDateTime.now());
        accountRepository.save(account);
    }
}
