package com.app.matrimonial.service;


import com.app.matrimonial.model.BankDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankDetailsService {

    BankDetails saveDetails(BankDetails bankDetails);
    List<BankDetails> get();
}
