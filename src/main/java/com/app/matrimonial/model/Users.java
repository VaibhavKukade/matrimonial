package com.app.matrimonial.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email_id")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "address")
    private String address;

    @Column(name = "confirm_password")
    private String confirmPassword;

    @Column(name = "approved")
    private Boolean approved;

    @Column(name = "role")
    private String role;

    @Column(name = "profile_pic")
    private String profile_pic;


    @Column(name = "bank_name")
    private String bank_name;

    @Column(name = "accountNo")
    private String accountNo;

    @Column(name = "ifscCode")
    private String ifscCode;

    @Column(name = "upiId")
    private String upiId;

    @Column(name = "imageName")
    private String imageName;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "created_date")
    private String  created_date;


}
