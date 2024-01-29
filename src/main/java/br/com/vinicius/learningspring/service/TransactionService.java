package br.com.vinicius.learningspring.service;

import java.util.List;

import br.com.vinicius.learningspring.dto.CreateTransactionDto;
import br.com.vinicius.learningspring.model.Transaction;

public interface TransactionService {

    Transaction createTransaction(final CreateTransactionDto transactionData);

    List<Transaction> readTransactions();

}