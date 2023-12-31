package com.app.matrimonial.repository;

import com.app.matrimonial.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    Users findByUsername(String username);
}
