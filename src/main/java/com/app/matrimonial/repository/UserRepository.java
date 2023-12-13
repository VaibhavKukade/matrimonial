package com.app.matrimonial.repository;

import com.app.matrimonial.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @Query("SELECT u FROM Users u WHERE u.email = ?1")
    Optional<Users> findUserbyUsername(String username);
}
