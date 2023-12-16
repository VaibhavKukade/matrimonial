package com.app.matrimonial.controller;

import com.app.matrimonial.model.BankDetails;
import com.app.matrimonial.service.BankDetialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bank/")
public class BankDetailsController {

    @Autowired
    BankDetialsService bankDetailsService;

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
    public ResponseEntity<BankDetails> getAllExpenses() {
        BankDetails bankDetails = bankDetailsService.get();
        if (bankDetails == null) {
            return (ResponseEntity<BankDetails>) ResponseEntity.notFound();
        } else {
            return ResponseEntity.ok(bankDetails);
        }
    }
}