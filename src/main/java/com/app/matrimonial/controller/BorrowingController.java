package com.app.matrimonial.controller;

import com.app.matrimonial.model.Borrowing;
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
    //testing


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
}


