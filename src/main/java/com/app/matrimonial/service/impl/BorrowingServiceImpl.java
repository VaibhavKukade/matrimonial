package com.app.matrimonial.service.impl;

import com.app.matrimonial.model.Borrowing;
import com.app.matrimonial.repository.BorrowingRepository;
import com.app.matrimonial.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowingServiceImpl implements BorrowingService {

    @Autowired
    BorrowingRepository borrowingRepository;

    @Override
    public Borrowing AddDetails(Borrowing borrowing) {
        return borrowingRepository.save(borrowing);
    }

    @Override
    public List<Borrowing> findAll() {
        return borrowingRepository.findAll();
    }

    @Override
    public List<Borrowing> getUnapprovedBorrowing() {
        return borrowingRepository.getUnapprovedBorrowing();
    }

    @Override
    public List<Borrowing> getApprovedBorrowing() {return borrowingRepository.getApprovedBorrowing();}

    @Override
    public List<Borrowing> getBorrowingByUsername(String username) {
        return borrowingRepository.getBorrowingByUsername(username);
    }


    @Override
    public Optional<Borrowing> findById(Long id) {
        return borrowingRepository.findById(Math.toIntExact(id));
    }
}
