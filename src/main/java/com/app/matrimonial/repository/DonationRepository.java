package com.app.matrimonial.repository;

import com.app.matrimonial.model.BankDetails;
import com.app.matrimonial.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation,Long> {


    @Query("SELECT d from Donation d where  d.status is null")
    List<Donation> getUnapprovedDonations();

    @Query("SELECT d from Donation d where  d.status is true ")
    List<Donation> getApprovedDonations();

    @Query("SELECT d from Donation d where  d.username=?1")
    List<Donation> getDonationsByUsername(String username);


}