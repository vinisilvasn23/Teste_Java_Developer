package br.com.vinicius.learningspring.controller;

import br.com.vinicius.learningspring.dto.CreateTransactionDto;
import br.com.vinicius.learningspring.model.Transaction;
import br.com.vinicius.learningspring.service.TransactionService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody final CreateTransactionDto transactionData)
            throws Exception {

        final Transaction createdTransaction = transactionService.createTransaction(transactionData);

        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<Transaction>> readTransactions() {
        final List<Transaction> allTransactions = transactionService.readTransactions();
        return new ResponseEntity<>(allTransactions, HttpStatus.OK);
    }

}