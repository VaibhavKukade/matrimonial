package com.app.matrimonial.controller;

import com.app.matrimonial.model.Borrowing;
import com.app.matrimonial.model.Expenses;
import com.app.matrimonial.service.ExpensesService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    public ResponseEntity<JsonNode> getAllExpenses() {
        List<Expenses> expenses = expensesService.findAll();
        if (expenses!=null && expenses.size()>0){
            double total= 0;
            for (Expenses expenses1:expenses){
                total+=expenses1.getAmount().doubleValue();
            }
            ObjectMapper objectMapper=new ObjectMapper();
            JsonNode jsonNode=objectMapper.createObjectNode();
            ((ObjectNode) jsonNode).put("totalAmount", total);
            ((ObjectNode) jsonNode).putPOJO("data", expenses);

            return ResponseEntity.ok(jsonNode);
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    // Get Expense by ID
    @GetMapping("/{id}")
    public ResponseEntity<Expenses> getExpenseById(@PathVariable Long id) {
        Optional<Expenses> expense = expensesService.findById(id);
        return expense.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    }

