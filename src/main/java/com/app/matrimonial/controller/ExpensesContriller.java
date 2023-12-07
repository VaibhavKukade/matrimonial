package com.app.matrimonial.controller;

import com.app.matrimonial.model.Expenses;
import com.app.matrimonial.service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/expenses")

public class ExpensesContriller {

    @Autowired
    ExpensesService expensesService;

    @PostMapping("/addExpenses")
    public ResponseEntity<String> addExpense(@RequestBody Expenses expense) {
        try {
            expensesService.AddDetails(expense);
            return ResponseEntity.ok("Expense added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add expense");
        }
    }

    // Get All Expenses
    @GetMapping("/getAll")
    public ResponseEntity<List<Expenses>> getAllExpenses() {
        List<Expenses> expenses = expensesService.findAll();
        return ResponseEntity.ok(expenses);
    }

    // Get Expense by ID
    @GetMapping("/{id}")
    public ResponseEntity<Expenses> getExpenseById(@PathVariable Long id) {
        Optional<Expenses> expense = expensesService.findById(id);
        return expense.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    }

