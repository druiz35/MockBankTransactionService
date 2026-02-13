package com.druiz.banktransactionapi.controllers;

import com.druiz.banktransactionapi.dto.TransactionDTO;
import com.druiz.banktransactionapi.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity getAllTransactions(@RequestParam(name="user_id", required=false) String userId) {
        if (!(userId == null)) {
            return transactionService.getTransactionsByUserId(userId);
        }
        return transactionService.getAllTransactions();
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> submitTransaction(@RequestBody TransactionDTO transactionDto) {
        return transactionService.saveTransaction(transactionDto);
    }

}
