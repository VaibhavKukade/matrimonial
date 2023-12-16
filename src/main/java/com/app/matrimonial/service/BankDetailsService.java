package com.app.matrimonial.service;


import com.app.matrimonial.model.BankDetails;
import org.springframework.stereotype.Service;

@Service
public interface BankDetailsService {

    BankDetails saveDetails(BankDetails bankDetails);
    BankDetails get();
}
