package br.com.vinicius.learningspring.model;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private User client;

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private User company;

    @Column(columnDefinition = "DECIMAL", nullable = false)
    private BigDecimal amount;

    @Column(length = 10, nullable = false)
    private String type;

    @CreationTimestamp
    @Column(updatable = false)
    private Date date;

    public Transaction() {
    }

    public Transaction(User foundClient, User foundCompany, BigDecimal transactionAmount, BigDecimal feeAmount,
            String type) {
    }

    public Transaction(User client, User company, BigDecimal amount, String type) {
        this.client = client;
        this.company = company;
        this.amount = amount;
        this.type = type;
        this.date = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getCompany() {
        return company;
    }

    public void setCompany(User company) {
        this.company = company;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setValue(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}