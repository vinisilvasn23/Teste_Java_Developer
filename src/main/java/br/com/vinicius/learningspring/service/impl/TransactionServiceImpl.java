package br.com.vinicius.learningspring.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vinicius.learningspring.dto.CreateTransactionDto;
import br.com.vinicius.learningspring.exception.AppException;
import br.com.vinicius.learningspring.model.Transaction;
import br.com.vinicius.learningspring.model.User;
import br.com.vinicius.learningspring.repository.TransactionRepository;
import br.com.vinicius.learningspring.repository.UserRepository;
import br.com.vinicius.learningspring.service.EmailService;
import br.com.vinicius.learningspring.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository,
            EmailService emailService) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Transactional
    public Transaction createTransaction(final CreateTransactionDto transactionData) {
        final User foundClient = userRepository.findById(transactionData.getClientId())
                .orElseThrow(() -> new AppException("clientNotFound", HttpStatus.NOT_FOUND));

        final User foundCompany = userRepository.findById(transactionData.getCompanyId())
                .orElseThrow(() -> new AppException("companyNotFound", HttpStatus.NOT_FOUND));

        final BigDecimal transactionAmount = transactionData.getAmount();
        final BigDecimal feePercentage = foundCompany.getFee();
        BigDecimal feeAmount = transactionAmount.multiply(feePercentage).divide(BigDecimal.valueOf(100));
        BigDecimal transactionAfterFee = transactionAmount.subtract(feeAmount);

        try {
            if ("withdrawal".equals(transactionData.getType())) {
                if (foundCompany.getBalance().compareTo(transactionAmount) < 0) {
                    throw new AppException("balanceNotSufficient", HttpStatus.FORBIDDEN);
                }
                foundCompany.setBalance(foundCompany.getBalance().subtract(transactionAmount));
                foundClient.setBalance(foundClient.getBalance().add(transactionAfterFee));

                String companyEmail = foundCompany.getEmail();
                String companySubject = "Nova Transação Realizada";
                String companyMessage = "Uma nova transação foi realizada em sua conta. Retirada efetuada no valor de $"
                        + transactionAmount;
                emailService.sendNotificationEmail(companyEmail, companySubject, companyMessage);
            } else {
                if (foundClient.getBalance().compareTo(transactionAmount) < 0) {
                    throw new AppException("balanceNotSufficient", HttpStatus.FORBIDDEN);
                }
                foundClient.setBalance(foundClient.getBalance().subtract(transactionAmount));
                foundCompany.setBalance(foundCompany.getBalance().add(transactionAfterFee));

                String clientEmail = foundClient.getEmail();
                String clientSubject = "Nova Transação Realizada";
                String clientMessage = "Uma nova transação foi realizada em sua conta. Transferência efetuada no valor de $"
                        + transactionAmount;
                emailService.sendNotificationEmail(clientEmail, clientSubject, clientMessage);
            }

            final Transaction newTransaction = new Transaction(foundClient, foundCompany, transactionAmount,
                    transactionData.getType());

            return transactionRepository.save(newTransaction);
        } catch (EmailException e) {
            e.printStackTrace();
            throw new AppException("errorSendingEmail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Transaction> readTransactions() {
        return transactionRepository.findAll();
    }
}
