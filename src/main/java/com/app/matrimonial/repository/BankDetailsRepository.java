package com.app.matrimonial.repository;

import com.app.matrimonial.model.BankDetails;
import com.app.matrimonial.service.BankDetialsService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails,Long> {

    @Query("SELECT b FROM BankDetails b WHERE b.status=true")
     BankDetails get();
}
