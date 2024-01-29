package br.com.vinicius.learningspring.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreateTransactionDto {

    @NotNull(message = "Client id is required")
    private Long clientId;

    @NotNull(message = "Company id is required")
    private Long companyId;

    @NotNull(message = "Value id is required")
    @DecimalMin(value = "0.01", message = "Value must be higher than 0.01")
    private BigDecimal amount;

    @NotNull(message = "Type id is required")
    @NotEmpty(message = "Transaction type cannot be empty")
    @Pattern(regexp = "(withdrawal|transfer)", message = "Invalid transaction type")
    private String type;

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}