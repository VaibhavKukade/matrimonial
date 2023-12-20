package com.app.matrimonial.controller;

import com.app.matrimonial.model.BankDetails;
import com.app.matrimonial.service.BankDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bank/")
public class BankDetailsController {


    @Autowired
    BankDetailsService bankDetailsService;

    @PostMapping("details/save")
    public ResponseEntity<String> saveBankDetails(@RequestBody BankDetails bankDetails) {
        try {
            bankDetailsService.saveDetails(bankDetails);
            return ResponseEntity.ok("Bank Details added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add details");
        }
    }

    @GetMapping("get")
    public ResponseEntity<List<BankDetails>> getBankDetails() {
        List<BankDetails> bankDetails = bankDetailsService.get();
        if (bankDetails == null) {
            return  ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(bankDetails);
        }
    }

    @PutMapping("details/update")
    public ResponseEntity<String> updateBankDetails(@RequestBody BankDetails bankDetails) {
        try {
            bankDetailsService.saveDetails(bankDetails);
            return ResponseEntity.ok("Bank Details updated  successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update details");
        }
    }
}
