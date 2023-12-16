package com.app.matrimonial.service.impl;

import com.app.matrimonial.model.BankDetails;
import com.app.matrimonial.model.Borrowing;
import com.app.matrimonial.repository.BankDetailsRepository;
import com.app.matrimonial.service.BankDetialsService;
import org.springframework.beans.factory.annotation.Autowired;

public class BankDetailsServiceImpl implements BankDetialsService {

    @Autowired
    BankDetailsRepository bankDetailsRepository;

    @Override
    public BankDetails saveDetails(BankDetails bankDetails) {
        return bankDetailsRepository.save(bankDetails);
    }

    @Override
    public  BankDetails get(){
        return bankDetailsRepository.get();
    }


}
