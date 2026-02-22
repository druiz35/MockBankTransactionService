package com.druiz.mockbanktransactionservice.services;

import com.druiz.mockbanktransactionservice.dto.TransactionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.druiz.mockbanktransactionservice.repositories.TransactionCustomRepository;

import java.util.*;

@Service("transactionService")
public class TransactionService {
    private final TransactionCustomRepository transactionCustomRepository;


    public TransactionService(TransactionCustomRepository transactionCustomRepository) {
        this.transactionCustomRepository = transactionCustomRepository;
    }

    public ResponseEntity<TransactionDTO> submitTransaction(TransactionDTO transactionDto) {
        transactionDto.id = UUID.randomUUID();
        transactionCustomRepository.submitTransaction(transactionDto);
        return new ResponseEntity<>(transactionDto, HttpStatus.OK);
    }

    public ResponseEntity getAllTransactions() {
        List<TransactionDTO> transactions = transactionCustomRepository.getAll();
        return new ResponseEntity(transactions, HttpStatus.OK);
    }

    public ResponseEntity getTransactionsByUserId(String userId) {
        List<TransactionDTO> transactions = transactionCustomRepository.getByUserId(userId);
        return new ResponseEntity(transactions, HttpStatus.OK);
    }

    public ResponseEntity getTransactionsByOriginProduct(String originProduct) {
        List<TransactionDTO> transactions = transactionCustomRepository.getByOriginProduct(originProduct);
        return new ResponseEntity(transactions, HttpStatus.OK);
    }

    public ResponseEntity getTransactionsByDestinationProduct(String destinationProduct) {
        List<TransactionDTO> transactions = transactionCustomRepository.getByDestinationProduct(destinationProduct);
        return new ResponseEntity(transactions, HttpStatus.OK);
    }
}
