package br.com.vinicius.learningspring.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class UserDto {

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name cannot be empty")
    @Size(max = 92, message = "Name must be lower than 92 characters long")
    private String name;

    @NotNull(message = "Cpf/Cnpj is required")
    @NotEmpty(message = "Cpf/Cnpj cannot be empty")
    @Size(max = 14, message = "Cpf/cnpj must characters long")
    private String cpfCnpj;

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email cannot be empty")
    @Size(max = 62, message = "Email must be lower than 62 characters long")
    private String email;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @NotNull(message = "User type is required (client/company)")
    @Pattern(regexp = "(client|company)", message = "User type must be client or company")
    private String type;

    private BigDecimal fee;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setcpfCnpj(String cpfCnpj) {
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

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
}