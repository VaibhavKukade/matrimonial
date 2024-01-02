package com.app.matrimonial.repository;

import com.app.matrimonial.model.Borrowing;
import com.app.matrimonial.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Integer>
{

    @Query("SELECT e from Expenses e where  (e.dateOfExpense between ?2 and ?1)")
    List<Expenses> getAll(Date date, Date oldDate);

    @Query("SELECT e from Expenses e where  e.userId=?1 and (e.dateOfExpense between ?3 and ?2) order by e.dateOfExpense DESC")
    List<Expenses> getExpensesByUserId(Long userId,Date date,Date oldDate);
}
