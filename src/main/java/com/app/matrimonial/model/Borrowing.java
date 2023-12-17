package com.app.matrimonial.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "borrowing")
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "borrowing_id")
    private int borrowingId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "borrower_name")
    private String borrowerName;

    @Column(name = "borrowed_date")
    private Date borrowedDate;

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "status")
    private Boolean status;
    @Column(name = "user_id")
    private long userId;

}



