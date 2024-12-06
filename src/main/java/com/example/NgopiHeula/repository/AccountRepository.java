package com.example.NgopiHeula.repository;

import com.example.NgopiHeula.model.dto.account.AccountViewDTO;
import com.example.NgopiHeula.model.dto.dropdown.DropdownDTO;
import com.example.NgopiHeula.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String > {

    @Query("""
			SELECT COUNT(acc.username) 
			FROM Account AS acc
			WHERE acc.username = :username	""")
    public Long countByUsername(@Param("username") String username);

    @Query("""
            SELECT new com.example.NgopiHeula.model.dto.account.AccountViewDTO(acc.username,acc.role)
            FROM Account as acc
            WHERE acc.username LIKE %:username%
            """)
    public List<AccountViewDTO> getAllAccount(@Param("username") String username);

    @Query("""
            SELECT new com.example.NgopiHeula.model.dto.dropdown.DropdownDTO(acc.username,acc.username)
            FROM Account as acc
            WHERE acc.role = 'cashier'
            """)
    public List<DropdownDTO> findAllCashier();
}
