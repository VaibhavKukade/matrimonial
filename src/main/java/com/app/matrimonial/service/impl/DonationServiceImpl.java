package com.app.matrimonial.service.impl;


import com.app.matrimonial.model.Donation;
import com.app.matrimonial.model.Users;
import com.app.matrimonial.repository.DonationRepository;
import com.app.matrimonial.repository.UserRepository;
import com.app.matrimonial.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class DonationServiceImpl implements DonationService {

    @Autowired
    DonationRepository donationRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Donation saveSonation(Donation donation){

        return donationRepository.save(donation);
    }

    @Override
    public List<Donation> getAllApprovedDonations(String filter){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(simpleDateFormat.format(date));
        } catch (Exception e) {
            return null;
        }
        Date oldDate = getOldDate(date, filter, simpleDateFormat);
        if (oldDate!=null) {
            return donationRepository.getApprovedDonations(simpleDateFormat.format(date),simpleDateFormat.format(oldDate));
        }else{
            return null;
        }
    }

    @Override
    public List<Donation> getAllUnapprovedDonations(){
        return  donationRepository.getUnapprovedDonations();
    }

    @Override
    public List<Donation> getDonationByUsername(String username){
        return  donationRepository.getDonationsByUsername(username);
    }

    @Override
    public List<Donation> getDonationByContactNumber(String contactNumber){
        Users user=userRepository.getUsersByMobileNo(contactNumber);
        if (user!=null){
            return  donationRepository.getDonationsByUsername(user.getUsername());
        }else{
            return null;
        }

    }

    @Override
    public List<Donation> getAllDonations(String filter){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(simpleDateFormat.format(date));
        } catch (Exception e) {
            return null;
        }
        Date oldDate = getOldDate(date, filter, simpleDateFormat);
        if (oldDate!=null) {
            return donationRepository.getAllDonations(simpleDateFormat.format(date),simpleDateFormat.format(oldDate));
        }else{
            return null;
        }
    }


    private Date getOldDate(Date date, String filter, SimpleDateFormat simpleDateFormat) {
        LocalDateTime oldLocalDate = null;
        if (filter.equalsIgnoreCase("Day")) {
            oldLocalDate = LocalDateTime.now().minusHours(24);
        } else if (filter.equalsIgnoreCase("Week")) {
            oldLocalDate = LocalDateTime.now().minusWeeks(1);
        } else if (filter.equalsIgnoreCase("Month")) {
            oldLocalDate = LocalDateTime.now().minusDays(30);
        }else if (filter.equalsIgnoreCase("all")){
            oldLocalDate = LocalDateTime.of(2023, 01, 01,0,0,0);
        }

        Date oldDate = Date.from(oldLocalDate.atZone(ZoneId.systemDefault()).toInstant());
        Date formattedDate = null;
        try {
            formattedDate = simpleDateFormat.parse(simpleDateFormat.format(oldDate));
        } catch (Exception e) {
            return null;
        }
        return formattedDate;
    }
}
