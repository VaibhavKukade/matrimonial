package com.app.matrimonial.service;


import com.app.matrimonial.model.Donation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DonationService {

    Donation saveSonation(Donation donation);


    List<Donation> getAllApprovedDonations();

    List<Donation> getAllUnapprovedDonations();

    List<Donation> getDonationByUsername(String username);

}
