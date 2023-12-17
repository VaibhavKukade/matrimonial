package com.app.matrimonial.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor@Entity
@Table(name = "donation")
public class Donation {

    @Id
    @GeneratedValue
    @Column(name = "uid")
    private Long id;

    @Column(name = "username",columnDefinition = "VARCHAR(50)")
    private String username;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "note",columnDefinition = "TEXT")
    private String note;

    @Column(name = "screenshot")
    private String screenshot;

    @Column(name = "date")
    private String date;

    @Column(name = "status")
    private Boolean  status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Donation{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", amount=" + amount +
                ", note='" + note + '\'' +
                ", screenshot='" + screenshot + '\'' +
                ", date='" + date + '\'' +
                ", status=" + status +
                '}';
    }
}
