package com.tuan.authservice.repository;

import com.tuan.authservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {
    Optional<Account> findDistinctByGmail(String s);
    Optional<Account> findDistinctByPhoneNumber(String s);

}
