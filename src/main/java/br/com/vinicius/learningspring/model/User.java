package br.com.vinicius.learningspring.model;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 92, nullable = false)
    private String name;

    @Column(length = 14, nullable = false, unique = true)
    private String cpfCnpj;

    @Column(length = 62, nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(columnDefinition = "TEXT", nullable = false)
    private String password;

    @Column(length = 8, nullable = false)
    private String type;

    @Column(columnDefinition = "DECIMAL(19,2)")
    @ColumnDefault("0.00")
    private BigDecimal balance;

    @Column(columnDefinition = "DECIMAL(19,2)")
    @ColumnDefault("0.00")
    private BigDecimal fee;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Transaction> transactionsAsClient;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Transaction> transactionsAsCompany;

    public User() {
    }

    public User(String name, String cpfCnpj, String email, String password, String type) {
        this.name = name;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.password = password;
        this.type = type;
        this.balance = BigDecimal.ZERO;
        this.fee = BigDecimal.ZERO;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
}
