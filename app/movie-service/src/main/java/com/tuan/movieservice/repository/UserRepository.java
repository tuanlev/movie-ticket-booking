package com.tuan.movieservice.repository;

import com.tuan.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findDistinctByGmail(String s);
    Optional<User> findDistinctByPhoneNumber(String s);
    Optional<User> findDistinctByUsername(String s);
}
