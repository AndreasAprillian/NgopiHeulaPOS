package com.example.NgopiHeula.service.abstraction;

import com.example.NgopiHeula.model.dto.account.AccountUpsertDTO;
import com.example.NgopiHeula.model.dto.account.AccountViewDTO;
import com.example.NgopiHeula.validation.Compare;

import java.util.List;

public interface AccountService {
    public String getAccountRole(String username);

    public List<AccountViewDTO> getAllAccount(String username);

    public boolean checkExistingAccount(String username);

    public void register(AccountUpsertDTO dto);
}
