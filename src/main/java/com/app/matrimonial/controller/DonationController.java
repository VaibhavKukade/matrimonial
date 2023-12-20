package com.app.matrimonial.controller;


import com.app.matrimonial.model.Borrowing;
import com.app.matrimonial.model.Donation;
import com.app.matrimonial.service.DonationService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/donation/")
public class DonationController {

    @Autowired
    DonationService donationService;

    @PostMapping("save")
    public ResponseEntity<String> saveDonation(@RequestBody Donation donation) {
        try {
            donationService.saveSonation(donation);
            return ResponseEntity.ok("Donation added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add details");
        }

    }

    @GetMapping("get/approved")
    public ResponseEntity<JsonNode> getAllApprovedDonations() {
        try {
            List<Donation> donationList=donationService.getAllApprovedDonations();
            if (donationList!=null && donationList.size()>0){
                double total=0;
                for (Donation donation:donationList){
                    total+=donation.getAmount();
                }
                ObjectMapper objectMapper=new ObjectMapper();
                JsonNode jsonNode=objectMapper.createObjectNode();
                ((ObjectNode) jsonNode).put("totalAmount", total);
                ((ObjectNode) jsonNode).putPOJO("data", donationList);

                return ResponseEntity.ok(jsonNode);
            }else{
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return  ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("get/all")
    public ResponseEntity<List<Donation>> getAllDonations() {
        try {
            List<Donation> donationList=donationService.getAllDonations();
            if (donationList!=null && donationList.size()>0){
                return ResponseEntity.ok(donationList);
            }else{
                return  ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("get/unapproved")
    public ResponseEntity<List<Donation>> getAllUnapprovedUsers() {
        try {
            List<Donation> donationList=donationService.getAllUnapprovedDonations();
            if (donationList!=null && donationList.size()>0){
                return ResponseEntity.ok(donationList);
            }else{
                return  ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return  ResponseEntity.internalServerError().build();
        }

    }
    @GetMapping("get/byusername")
    public ResponseEntity<List<Donation>> getDonationsByUsername(@RequestParam String  username) {
        try {
            List<Donation> donationList=donationService.getDonationByUsername(username);
            if (donationList!=null && donationList.size()>0){
                return ResponseEntity.ok(donationList);
            }else{
                return  ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return  ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping("update")
    public ResponseEntity<String> updateDonation(@RequestBody Donation donation){
        try {
            donationService.saveSonation(donation);
            return ResponseEntity.ok("Donation updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update details");
        }
    }
}
