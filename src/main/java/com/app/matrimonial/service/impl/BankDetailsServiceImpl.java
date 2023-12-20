package com.app.matrimonial.service.impl;

import com.app.matrimonial.model.BankDetails;
import com.app.matrimonial.repository.BankDetailsRepository;
import com.app.matrimonial.service.BankDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankDetailsServiceImpl implements BankDetailsService {

    @Autowired
    BankDetailsRepository bankDetailsRepository;

    @Override
    public BankDetails saveDetails(BankDetails bankDetails) {

        return bankDetailsRepository.save(bankDetails);
    }

    @Override
    public List<BankDetails> get(){
        return bankDetailsRepository.get();
    }


}
