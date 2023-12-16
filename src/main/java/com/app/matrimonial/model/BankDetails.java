package com.app.matrimonial.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bank_details")
public class BankDetails {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return "BankDetails{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", ifscCode='" + ifscCode + '\'' +
                ", upiId='" + upiId + '\'' +
                ", imageName='" + imageName + '\'' +
                ", status=" + status +
                '}';
    }
}
