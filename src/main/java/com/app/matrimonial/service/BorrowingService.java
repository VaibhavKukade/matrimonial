package com.app.matrimonial.service;

import com.app.matrimonial.model.Borrowing;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BorrowingService {


    Borrowing AddDetails(Borrowing borrowing);

    List<Borrowing> findAll();

    Optional<Borrowing> findById(Long id);
}
