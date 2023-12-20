package com.app.matrimonial.repository;

import com.app.matrimonial.model.BankDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails,Long> {

    @Query("SELECT b FROM BankDetails b WHERE b.status=true")
    List<BankDetails> get();
}
