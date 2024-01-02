package com.app.matrimonial.repository;

import com.app.matrimonial.model.BankDetails;
import com.app.matrimonial.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation,Long> {


    @Query("SELECT d from Donation d where  d.status is null")
    List<Donation> getUnapprovedDonations();

    @Query("SELECT d from Donation d where  d.status = true and (d.date between ?2 and ?1 )")
    List<Donation> getApprovedDonations(String date,String oldDate);

    @Query("SELECT d from Donation d where   (d.date between ?2 and ?1 )")
    List<Donation> getAllDonations(String date,String oldDate);

    @Query("SELECT d from Donation d where  d.username=?1")
    List<Donation> getDonationsByUsername(String username);

    @Query("SELECT d from Donation d where  d.username=?1 and (d.date between ?3 and ?2 ) order by d.date DESC ")
    List<Donation> getAllDonationsByUsername(String username,String date,String oldDate);


}
