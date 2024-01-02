package com.app.matrimonial.controller;

import com.app.matrimonial.model.Borrowing;
import com.app.matrimonial.model.Donation;
import com.app.matrimonial.service.BorrowingService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Formattable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/borrowing")
public class BorrowingController {
    @Autowired
    BorrowingService borrowingService;

    @PostMapping("/addBorrowing")
    public ResponseEntity<String> addExpense(@RequestBody Borrowing borrowing) {
        try {
            Borrowing save=borrowingService.AddDetails(borrowing);
            if (save!=null) {
                return ResponseEntity.ok("Borrowing added successfully");
            }else{
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add expense");
        }
    }

    @PutMapping("/updateBorrowing")
    public ResponseEntity<String> UpdateExpense(@RequestBody Borrowing borrowing) {
        try {
            Borrowing update=borrowingService.AddDetails(borrowing);
            if (update!=null) {
                return ResponseEntity.ok("Borrowing added successfully");
            }else{
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add expense");
        }
    }


    // Get All Expenses
    @GetMapping("/getAll")
    public ResponseEntity<List<Borrowing>> getAllExpenses(@RequestParam String filter) {

        List<Borrowing> borrowing = borrowingService.findAll(filter);
        if (borrowing!=null && borrowing.size()>0) {
            return ResponseEntity.ok(borrowing);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    // Get Expense by ID
    @GetMapping("/{id}")
    public ResponseEntity<Borrowing> getBorrowingById(@PathVariable Long id) {
        Optional<Borrowing> borrowing = borrowingService.findById(id);
        return borrowing.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/get/unapproved")
    public ResponseEntity<List<Borrowing>> getUnapprovedBorrowing() {
        try {
            List<Borrowing> borrowingList=borrowingService.getUnapprovedBorrowing();
            if (borrowingList!=null && borrowingList.size()>0){
                return ResponseEntity.ok(borrowingList);
            }else{
                return  ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            return  ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/get/approved")
    public ResponseEntity<JsonNode> getApprovedBorrowing(@RequestParam String filter) {
        try {
            List<Borrowing> borrowingList=borrowingService.getApprovedBorrowing(filter);
            if (borrowingList!=null && borrowingList.size()>0){
                double total=0;
                for (Borrowing borrowing:borrowingList){
                    total+=borrowing.getAmount()!=null?borrowing.getAmount():0;
                }
                ObjectMapper objectMapper=new ObjectMapper();
                JsonNode jsonNode=objectMapper.createObjectNode();
                ((ObjectNode) jsonNode).put("totalAmount", total);
                ((ObjectNode) jsonNode).putPOJO("data", borrowingList);

                return ResponseEntity.ok(jsonNode);
            }else{
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            return  ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/get/byusername")
    public ResponseEntity<List<Borrowing>> getBorrowingByUsername(@RequestParam  String username) {
        try {
            List<Borrowing> borrowingList=borrowingService.getBorrowingByUsername(username);
            if (borrowingList!=null && borrowingList.size()>0){
                return ResponseEntity.ok(borrowingList);
            }else{
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            return  ResponseEntity.internalServerError().build();
        }

    }
}


