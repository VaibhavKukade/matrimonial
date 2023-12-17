package com.app.matrimonial.controller;

import com.app.matrimonial.model.Borrowing;
import com.app.matrimonial.model.Donation;
import com.app.matrimonial.model.Expenses;
import com.app.matrimonial.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            borrowingService.AddDetails(borrowing);
            return ResponseEntity.ok("Borrowing added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add expense");
        }
    }

    @PutMapping("/updateBorrowing")
    public ResponseEntity<String> UpdateExpense(@RequestBody Borrowing borrowing) {
        try {
            borrowingService.AddDetails(borrowing);
            return ResponseEntity.ok("Borrowing added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add expense");
        }
    }


    // Get All Expenses
    @GetMapping("/getAll")
    public ResponseEntity<List<Borrowing>> getAllExpenses() {
        List<Borrowing> borrowing = borrowingService.findAll();
        return ResponseEntity.ok(borrowing);
    }

    // Get Expense by ID
    @GetMapping("/{id}")
    public ResponseEntity<Borrowing> getBorrowingById(@PathVariable Long id) {
        Optional<Borrowing> borrowing = borrowingService.findById(id);
        return borrowing.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/get/unapproved")
    public ResponseEntity<List<Borrowing>> getUnapprovedBorrowing() {
        try {
            List<Borrowing> borrowingList=borrowingService.getUnapprovedBorrowing();
            if (borrowingList!=null && borrowingList.size()>0){
                return ResponseEntity.ok(borrowingList);
            }else{
                return (ResponseEntity<List<Borrowing>>) ResponseEntity.notFound();
            }
        } catch (Exception e) {
            return (ResponseEntity<List<Borrowing>>) ResponseEntity.internalServerError();
        }

    }

    @GetMapping("/get/approved")
    public ResponseEntity<List<Borrowing>> getApprovedBorrowing() {
        try {
            List<Borrowing> borrowingList=borrowingService.getApprovedBorrowing();
            if (borrowingList!=null && borrowingList.size()>0){
                return ResponseEntity.ok(borrowingList);
            }else{
                return (ResponseEntity<List<Borrowing>>) ResponseEntity.notFound();
            }
        } catch (Exception e) {
            return (ResponseEntity<List<Borrowing>>) ResponseEntity.internalServerError();
        }

    }

    @GetMapping("/get/byusername")
    public ResponseEntity<List<Borrowing>> getBorrowingByUsername(@RequestParam  String username) {
        try {
            List<Borrowing> borrowingList=borrowingService.getBorrowingByUsername(username);
            if (borrowingList!=null && borrowingList.size()>0){
                return ResponseEntity.ok(borrowingList);
            }else{
                return (ResponseEntity<List<Borrowing>>) ResponseEntity.notFound();
            }
        } catch (Exception e) {
            return (ResponseEntity<List<Borrowing>>) ResponseEntity.internalServerError();
        }

    }
}


