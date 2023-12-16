package com.app.matrimonial.service;


import com.app.matrimonial.model.BankDetails;
import com.app.matrimonial.model.Borrowing;
import org.springframework.stereotype.Service;

@Service
public interface BankDetialsService {

    BankDetails saveDetails(BankDetails bankDetails);
    BankDetails get();
}
