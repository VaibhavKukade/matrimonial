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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


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
        Users users = userRepository.existsByMobileNumber(newUser.getMobileNo());
        if (users != null) {
            return "Mobile Number is already registered";
        }

        userRepository.save(newUser);
        return "User registered successfully";
    }

    @Override
    public Users updateUser(Users newUser) {
        Users user = userRepository.save(newUser);
        return user;
    }


    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<Users> findUnapprovedUsers() {
        return userRepository.findUnapprovedUsers();
    }

    @Override
    public Users findUserByUsername(String username) {
        try {
            return userRepository.findByUsername(username);
        } catch (Exception e) {
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
    public JsonNode getUsersActivities(String username, Long id, String filter) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(simpleDateFormat.format(date));
        } catch (Exception e) {
            return null;
        }
        Date oldDate = getOldDate(date, filter, simpleDateFormat);

        List<Donation> donations = donationRepository.getAllDonationsByUsername(username, simpleDateFormat.format(date), simpleDateFormat.format(oldDate));
        List<Borrowing> borrowings = borrowingRepository.getBorrowingByUserId(id, date, oldDate);
        List<Expenses> expenses = expensesRepository.getExpensesByUserId(id, date, oldDate);


        donations.removeIf(donation -> donation.getStatus() == null || !donation.getStatus());

        borrowings.removeIf(borrowing -> borrowing.getStatus() == null || !borrowing.getStatus());



        double totalDonations= donations.stream()
                .mapToDouble(donation -> donation.getAmount() != null ? donation.getAmount() : 0)
                .sum();

        double totalBorrowings= borrowings.stream()
                .mapToDouble(borrowing -> borrowing.getAmount() != null ? borrowing.getAmount() : 0)
                .sum();
        double totalExpenses=0;
        for (Expenses expense:expenses){
            totalExpenses+=expense.getAmount()!=null?expense.getAmount().doubleValue():0;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.createObjectNode();
        ((ObjectNode) jsonNode).putPOJO("Total Donations", totalDonations);
        ((ObjectNode) jsonNode).putPOJO("Donation", donations);
        ((ObjectNode) jsonNode).putPOJO("Total Borrowings", totalBorrowings);
        ((ObjectNode) jsonNode).putPOJO("Borrowing", borrowings);
        ((ObjectNode) jsonNode).putPOJO("Total Expenses", totalExpenses);
        ((ObjectNode) jsonNode).putPOJO("Expenses", expenses);

        return jsonNode;
    }

    @Override
    public Users getUserByContactNumber(String contactno) {
        Users user = userRepository.getUsersByMobileNo(contactno);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public JsonNode getUser(String filter) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(simpleDateFormat.format(date));
        } catch (Exception e) {
            return null;
        }
        Date oldDate = getOldDate(date, filter, simpleDateFormat);
        if (oldDate != null) {
            List<Users> users = userRepository.getUsers(simpleDateFormat.format(date), simpleDateFormat.format(oldDate));
            if (users != null && users.size() > 0) {

                List<Users> approvedUsers = users.stream()
                        .filter(user -> user.getApproved() != null && user.getApproved())
                        .collect(Collectors.toList());

                List<Users> unapprovedUsers = users.stream()
                        .filter(user -> user.getApproved() != null && !user.getApproved())
                        .collect(Collectors.toList());

                List<Users> pendingUsers = users.stream().filter(user -> user.getApproved() == null).collect(Collectors.toList());

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.createObjectNode();
                ((ObjectNode) jsonNode).putPOJO("Approved Users", approvedUsers);
                ((ObjectNode) jsonNode).putPOJO("Unapproved Users", unapprovedUsers);
                ((ObjectNode) jsonNode).putPOJO("Pending Users", pendingUsers);

                return jsonNode;

            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    private Date getOldDate(Date date, String filter, SimpleDateFormat simpleDateFormat) {
        LocalDateTime oldLocalDate = null;
        if (filter.equalsIgnoreCase("Day")) {
            oldLocalDate = LocalDateTime.now().minusHours(24);
        } else if (filter.equalsIgnoreCase("Week")) {
            oldLocalDate = LocalDateTime.now().minusWeeks(1);
        } else if (filter.equalsIgnoreCase("Month")) {
            oldLocalDate = LocalDateTime.now().minusDays(30);
        } else if (filter.equalsIgnoreCase("all")) {
            oldLocalDate = LocalDateTime.of(2023, 01, 01, 0, 0, 0);
        }

        Date oldDate = Date.from(oldLocalDate.atZone(ZoneId.systemDefault()).toInstant());
        Date formattedDate = null;
        try {
            formattedDate = simpleDateFormat.parse(simpleDateFormat.format(oldDate));
        } catch (Exception e) {
            return null;
        }
        return formattedDate;
    }
}