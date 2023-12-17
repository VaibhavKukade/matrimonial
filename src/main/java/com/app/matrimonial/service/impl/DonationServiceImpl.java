package com.app.matrimonial.service.impl;


import com.app.matrimonial.model.Donation;
import com.app.matrimonial.repository.DonationRepository;
import com.app.matrimonial.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationServiceImpl implements DonationService {

    @Autowired
    DonationRepository donationRepository;

    @Override
    public Donation saveSonation(Donation donation){

        return donationRepository.save(donation);
    }

    @Override
    public List<Donation> getAllApprovedDonations(){
        return donationRepository.getApprovedDonations();
    }

    @Override
    public List<Donation> getAllUnapprovedDonations(){
        return  donationRepository.getUnapprovedDonations();
    }

    @Override
    public List<Donation> getDonationByUsername(String username){
        return  donationRepository.getDonationsByUsername(username);
    }


}
