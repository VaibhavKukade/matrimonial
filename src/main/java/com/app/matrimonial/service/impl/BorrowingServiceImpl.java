package com.app.matrimonial.service.impl;

import com.app.matrimonial.model.Borrowing;
import com.app.matrimonial.model.Users;
import com.app.matrimonial.repository.BorrowingRepository;
import com.app.matrimonial.repository.UserRepository;
import com.app.matrimonial.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.geom.RectangularShape;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingServiceImpl implements BorrowingService {

    @Autowired
    BorrowingRepository borrowingRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Borrowing AddDetails(Borrowing borrowing) {
        return borrowingRepository.save(borrowing);
    }

    @Override
    public List<Borrowing> findAll(String filter) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(simpleDateFormat.format(date));
        } catch (Exception e) {
            return null;
        }
        Date oldDate = getOldDate(date, filter, simpleDateFormat);
        if (oldDate != null) {
            return borrowingRepository.getAllBorrowing(date, oldDate);
        } else {
            return null;
        }
    }

    @Override
    public List<Borrowing> getUnapprovedBorrowing() {
        return borrowingRepository.getUnapprovedBorrowing();
    }

    @Override
    public List<Borrowing> getApprovedBorrowing(String filter) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(simpleDateFormat.format(date));
        } catch (Exception e) {
            return null;
        }
        Date oldDate = getOldDate(date, filter, simpleDateFormat);
        if (oldDate!=null) {
            return borrowingRepository.getApprovedBorrowing(date,oldDate);
        }else {
            return null;
        }
    }

    @Override
    public List<Borrowing> getBorrowingByUsername(String username) {
        return borrowingRepository.getBorrowingByUsername(username);
    }

    @Override
    public List<Borrowing> getDonationByContactNumber(String contactNumber) {
        Users users=userRepository.getUsersByMobileNo(contactNumber);
        if (users!=null){
            return borrowingRepository.getByUserId(users.getId());
        }else{
            return null;
        }
    }


    @Override
    public Optional<Borrowing> findById(Long id) {
        return borrowingRepository.findById(Math.toIntExact(id));
    }

    private Date getOldDate(Date date, String filter, SimpleDateFormat simpleDateFormat) {
        LocalDateTime oldLocalDate = null;
        if (filter.equalsIgnoreCase("Day")) {
            oldLocalDate = LocalDateTime.now().minusHours(24);
        } else if (filter.equalsIgnoreCase("Week")) {
            oldLocalDate = LocalDateTime.now().minusWeeks(1);
        } else if (filter.equalsIgnoreCase("Month")) {
            oldLocalDate = LocalDateTime.now().minusDays(30);
        }else if (filter.equalsIgnoreCase("all")){
            oldLocalDate = LocalDateTime.of(2023, 01, 01,0,0,0);
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
