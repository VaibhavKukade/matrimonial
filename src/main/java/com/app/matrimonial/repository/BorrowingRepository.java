package com.app.matrimonial.repository;

import com.app.matrimonial.model.Borrowing;
import com.app.matrimonial.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Integer> {

    @Query("SELECT b from Borrowing  b where  b.status is null")
    List<Borrowing> getUnapprovedBorrowing();

    @Query("SELECT b from Borrowing b where  b.status = true and (b.borrowedDate between ?2 and ?1)")
    List<Borrowing> getApprovedBorrowing(Date date, Date oldDate);

    @Query("SELECT b from Borrowing b where   (b.borrowedDate between ?2 and ?1)")
    List<Borrowing> getAllBorrowing(Date date, Date oldDate);

    @Query("SELECT b from Borrowing b where  b.borrowerName=?1")
    List<Borrowing> getBorrowingByUsername(String borrowerName);

    @Query("SELECT b from Borrowing b where  b.userId=?1 and (b.borrowedDate between ?3 and ?2) order by b.borrowedDate  desc")
    List<Borrowing> getBorrowingByUserId(Long userId,Date date,Date oldDate);
}
