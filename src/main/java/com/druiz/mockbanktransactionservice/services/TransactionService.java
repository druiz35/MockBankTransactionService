package com.druiz.mockbanktransactionservice.services;

import com.druiz.mockbanktransactionservice.dto.TransactionDTO;
import com.druiz.mockbanktransactionservice.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.druiz.mockbanktransactionservice.repositories.TransactionCustomRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@RequiredArgsConstructor
@Service("transactionService")
public class TransactionService {
    private final TransactionCustomRepository transactionCustomRepository;

    public Transaction submitTransaction(Transaction transaction) {
        transaction.setIdFromUUID(UUID.randomUUID());
        Instant instant = Instant.now();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        transaction.setDateTime(localDateTime);
        Transaction submittedTransaction = transactionCustomRepository.submitTransaction(transaction);
        return submittedTransaction;
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = transactionCustomRepository.getAll();
        return transactions;
    }

    public List<Transaction> getTransactionsByUserId(String userId) {
        List<Transaction> transactions = transactionCustomRepository.getByUserId(userId);
        return transactions;
    }

    public List<Transaction> getTransactionsByOriginProduct(String originProduct) {
        List<Transaction> transactions = transactionCustomRepository.getByOriginProduct(originProduct);
        return transactions;
    }

    public List<Transaction> getTransactionsByDestinationProduct(String destinationProduct) {
        List<Transaction> transactions = transactionCustomRepository.getByDestinationProduct(destinationProduct);
        return transactions;
    }
}
