package com.app.matrimonial.service.impl;

import com.app.matrimonial.model.Expenses;
import com.app.matrimonial.repository.ExpensesRepository;
import com.app.matrimonial.service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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
    public List<Expenses> findAll(String filter) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(simpleDateFormat.format(date));
        } catch (Exception e) {
            return null;
        }
        Date oldDate = getOldDate(date, filter, simpleDateFormat);
        if (oldDate!=null) {
            return expensesRepository.getAll(date,oldDate);
        }else {
            return null;
        }
    }

    @Override
    public Optional<Expenses> findById(Long id) {
        return expensesRepository.findById(Math.toIntExact(id));
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
