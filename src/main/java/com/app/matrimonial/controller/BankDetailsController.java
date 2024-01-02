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
            BankDetails save=bankDetailsService.saveDetails(bankDetails);
            if (save!=null) {
                return ResponseEntity.ok("Bank Details added successfully");
            }else{
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add details");
        }
    }

    @GetMapping("get")
    public ResponseEntity<List<BankDetails>> getBankDetails() {
        List<BankDetails> bankDetails = bankDetailsService.get();
        if (bankDetails == null) {
            return  ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(bankDetails);
        }
    }

    @PutMapping("details/update")
    public ResponseEntity<String> updateBankDetails(@RequestBody BankDetails bankDetails) {
        try {
            BankDetails update=bankDetailsService.saveDetails(bankDetails);
            if (update!=null) {
                return ResponseEntity.ok("Bank Details updated  successfully");
            }else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update details");
        }
    }
}
