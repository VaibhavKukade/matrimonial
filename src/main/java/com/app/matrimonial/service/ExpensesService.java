package com.app.matrimonial.service;

import com.app.matrimonial.model.Expenses;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface  ExpensesService {

    Expenses AddDetails(Expenses expense);

    List<Expenses> findAll();

    Optional<Expenses> findById(Long id);
}
