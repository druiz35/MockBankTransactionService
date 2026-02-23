package com.druiz.mockbanktransactionservice.controllers;

import com.druiz.mockbanktransactionservice.dto.TransactionDTO;
import com.druiz.mockbanktransactionservice.model.Transaction;
import com.druiz.mockbanktransactionservice.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    private final ModelMapper modelMapper;

    // get all transactions
    @GetMapping("/getAll")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        List<TransactionDTO> transactionsDto = transactions.stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(transactionsDto, HttpStatus.OK);
    }

    // get all transactions by date interval
    //@GetMapping
    // public ResponseEntity getAllTransactionsByDateInterval

    // get transactions by user id
    @GetMapping("/by_user_id")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByUserId(@RequestParam(name="user_id") String userId) {
        List<Transaction> transactions = transactionService.getTransactionsByUserId(userId);
        List<TransactionDTO> transactionsDto = transactions.stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(transactionsDto, HttpStatus.OK);
    }

    // get transactions by originProduct
    @GetMapping("/by_product_origin")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByOriginProduct(@RequestParam(name="origin_product") String originProduct) {
        List<Transaction> transactions = transactionService.getTransactionsByOriginProduct(originProduct);
        List<TransactionDTO> transactionsDto = transactions.stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(transactionsDto, HttpStatus.OK);
    }

    // get transactions by receiver
    @GetMapping("/by_product_destination")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByDestinationProduct(@RequestParam(name="destination_product") String destinationProduct) {
        List<Transaction> transactions = transactionService.getTransactionsByDestinationProduct(destinationProduct);
        List<TransactionDTO> transactionsDto = transactions.stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(transactionsDto, HttpStatus.OK);
    }

    // submit transaction
    @PostMapping
    public ResponseEntity<TransactionDTO> submitTransaction(@RequestBody TransactionDTO transactionDto) {
        Transaction convertedTransaction = convertToTransaction(transactionDto);
        Transaction newTransaction = transactionService.submitTransaction(convertedTransaction);
        TransactionDTO newTransactionDto = convertToDto(newTransaction);
        return new ResponseEntity<>(newTransactionDto, HttpStatus.OK);
    }

    private TransactionDTO convertToDto(Transaction transaction) {
        TransactionDTO transactionDto = modelMapper.map(transaction, TransactionDTO.class);
        return transactionDto;
    }

    private Transaction convertToTransaction(TransactionDTO transactionDto) {
        Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
        return transaction;
    }
}
