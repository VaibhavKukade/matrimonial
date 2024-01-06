package com.app.matrimonial.service;


import com.app.matrimonial.model.Donation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DonationService {

    Donation saveSonation(Donation donation);


    List<Donation> getAllApprovedDonations(String filter);

    List<Donation> getAllUnapprovedDonations();

    List<Donation> getDonationByUsername(String username);

    List<Donation> getDonationByContactNumber(String username);

    List<Donation> getAllDonations(String filter);

}
