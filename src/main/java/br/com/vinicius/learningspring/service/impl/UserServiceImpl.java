package br.com.vinicius.learningspring.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.vinicius.learningspring.dto.CreateDepositDto;
import br.com.vinicius.learningspring.dto.UserDto;
import br.com.vinicius.learningspring.exception.AppException;
import br.com.vinicius.learningspring.model.User;
import br.com.vinicius.learningspring.repository.UserRepository;
import br.com.vinicius.learningspring.service.UserService;
import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void checkEmailAndCpf(final UserDto userData) {
        if (userRepository.existsUserBycpfCnpj(userData.getCpfCnpj())) {
            throw new AppException("cpfCnpjAlreadyInUse", HttpStatus.CONFLICT);
        }
        if ("company".equals(userData.getType())
                && (userData.getCpfCnpj() == null || userData.getCpfCnpj().length() != 14)) {
            throw new AppException("cnpjCompanyFormat", HttpStatus.BAD_REQUEST);
        } else if ("user".equals(userData.getType())
                && (userData.getCpfCnpj() == null || userData.getCpfCnpj().length() != 11)) {
            throw new AppException("cpfUserFormat", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsUserByEmail(userData.getEmail())) {
            throw new AppException("emailAlreadyInUse", HttpStatus.CONFLICT);
        }
    }

    public User createUser(final UserDto userData) {
        checkEmailAndCpf(userData);

        if ("company".equals(userData.getType())
                && (userData.getFee() == null || userData.getFee().compareTo(BigDecimal.ZERO) <= 0)) {
            throw new AppException("feeCompanyRequired", HttpStatus.BAD_REQUEST);
        }

        final User newUser = new User(userData.getName(), userData.getCpfCnpj(), userData.getEmail(),
                userData.getPassword(), userData.getType());
        newUser.setFee(userData.getFee());

        return userRepository.save(newUser);
    }

    public List<User> readUsers() {
        return userRepository.findAll();
    }

    public User retrieveUser(final long id) {
        return userRepository.findById(id).orElseThrow(() -> new AppException("userNotFound", HttpStatus.NOT_FOUND));
    }

    public User partialUpdateUser(UserDto userData, long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (userData.getName() != null) {
            existingUser.setName(userData.getName());
        }

        if (userData.getName() != null) {
            existingUser.setName(userData.getName());
        }

        if (userData.getCpfCnpj() != null) {
            existingUser.setCpfCnpj(userData.getCpfCnpj());
        }

        if (userData.getEmail() != null) {
            existingUser.setEmail(userData.getEmail());
        }

        if (userData.getPassword() != null) {
            existingUser.setPassword(userData.getPassword());
        }

        if (userData.getType() != null) {
            existingUser.setType(userData.getType());
        }
        return userRepository.save(existingUser);
    }

    public void deleteUser(final long id) {
        final User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new AppException("userNotFound", HttpStatus.NOT_FOUND));
        userRepository.delete(foundUser);
    }

    public User createDeposit(final CreateDepositDto depositData, final long id) {
        final User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new AppException("userNotFound", HttpStatus.NOT_FOUND));

        BigDecimal depositValue = BigDecimal.valueOf(depositData.getValue());

        if (depositValue.compareTo(BigDecimal.ZERO) <= 0) {
            throw new AppException("depositValueInvalid", HttpStatus.BAD_REQUEST);
        }

        BigDecimal currentBalance = foundUser.getBalance();
        foundUser.setBalance(currentBalance.add(depositValue));

        return userRepository.save(foundUser);
    }
}