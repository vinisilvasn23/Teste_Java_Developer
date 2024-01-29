package br.com.vinicius.learningspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vinicius.learningspring.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
}