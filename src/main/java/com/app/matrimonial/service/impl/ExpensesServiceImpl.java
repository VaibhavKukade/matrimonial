package com.app.matrimonial.service.impl;

import com.app.matrimonial.model.Expenses;
import com.app.matrimonial.repository.ExpensesRepository;
import com.app.matrimonial.service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpensesServiceImpl implements ExpensesService {

    @Autowired
    ExpensesRepository expensesRepository;

    @Override
    public Expenses AddDetails(Expenses expense){
        return expensesRepository.save(expense);
    }

    @Override
    public List<Expenses> findAll() {
        return expensesRepository.findAll();
    }

    @Override
    public Optional<Expenses> findById(Long id) {
        return expensesRepository.findById(Math.toIntExact(id));
    }
}
