package com.app.matrimonial.repository;

import com.app.matrimonial.model.Borrowing;
import com.app.matrimonial.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Queue;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    boolean existsByUsername(String username);
    @Query("SELECT u FROM Users u WHERE u.mobileNo =?1")
    Users existsByMobileNumber(String contactNumber);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM Users u WHERE u.approved is null")
    List<Users> findUnapprovedUsers();

    Users findByUsername(String username);

    @Query("select  u from Users u where u.email=?1")
    Users findByEmail(String email);

    Users getUsersByMobileNo(String mobilenumber);

    @Query("SELECT u from Users u where   (u.created_date between ?2 and ?1)")
    List<Users> getUsers(String date, String oldDate);
}
