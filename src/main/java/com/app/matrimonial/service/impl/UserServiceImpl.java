package com.app.matrimonial.service.impl;

import com.app.matrimonial.model.Borrowing;
import com.app.matrimonial.model.Donation;
import com.app.matrimonial.model.Expenses;
import com.app.matrimonial.model.Users;
import com.app.matrimonial.repository.BorrowingRepository;
import com.app.matrimonial.repository.DonationRepository;
import com.app.matrimonial.repository.ExpensesRepository;
import com.app.matrimonial.repository.UserRepository;
import com.app.matrimonial.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@Primary
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DonationRepository donationRepository;

    @Autowired
    BorrowingRepository borrowingRepository;

    @Autowired
    ExpensesRepository expensesRepository;


    @Override
    public String signUpUser(Users newUser) {
        if (userRepository.existsByUsername(newUser.getUsername())) {
            return "Username is already taken";
        }
        if (userRepository.existsByEmail(newUser.getEmail())) {
            return "Email is already registered";
        }

        userRepository.save(newUser);
        return "User registered successfully";
    }

    @Override
    public Users updateUser(Users newUser) {
        Users user=userRepository.save(newUser);
        return  user;
    }


    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<Users> findUnapprovedUsers(){
        return userRepository.findUnapprovedUsers();
    }

    @Override
    public Users findUserByUsername(String username){
        try {
            return userRepository.findByUsername(username);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Users authenticateUser(Users loginUser) {
        Users userFromDB = userRepository.findByEmail(loginUser.getEmail());

        if (userFromDB != null && userFromDB.getPassword().equals(loginUser.getPassword())
                && userFromDB.getApproved() != null && userFromDB.getApproved()) {
            return userFromDB;
        } else {
            return null;
        }
    }

    @Override
    public JsonNode getUsersActivities(String username,Long id){

            List<Donation> donations=donationRepository.getDonationsByUsername(username);
            List<Borrowing> borrowings=borrowingRepository.getBorrowingByUserId(id);
            List<Expenses> expenses=expensesRepository.getExpensesByUserId(id);

            Double donationAmount=0d;
            Double borrowingAmount=0d;
            Double expensesAmount=0d;

            donationAmount = donations.stream()
                    .filter(Donation::getStatus)
                    .mapToDouble(Donation::getAmount)
                    .sum();

            borrowingAmount = borrowings.stream()
                    .filter(Borrowing::getStatus)
                    .mapToDouble(Borrowing::getAmount)
                    .sum();

            expensesAmount = expenses.stream()
                    .mapToDouble(expense -> expense.getAmount().doubleValue())
                    .sum();

            ObjectMapper objectMapper=new ObjectMapper();
            JsonNode jsonNode=objectMapper.createObjectNode();
            ((ObjectNode) jsonNode).put("totalDonation", donationAmount);
            ((ObjectNode) jsonNode).putPOJO("totalBorrowing", borrowingAmount);
            ((ObjectNode) jsonNode).putPOJO("totalExpenses", expensesAmount);

            return jsonNode;
    }
}