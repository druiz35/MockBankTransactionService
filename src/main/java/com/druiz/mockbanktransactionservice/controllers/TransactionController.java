package com.druiz.mockbanktransactionservice.controllers;

import com.druiz.mockbanktransactionservice.dto.TransactionDTO;
import com.druiz.mockbanktransactionservice.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // get all transactions
    @GetMapping("/getAll")
    public ResponseEntity getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    // get all transactions by date interval
    // public ResponseEntity getAllTransactionsByDateInterval

    // get transactions by user id
    @GetMapping("/byUserId")
    public ResponseEntity getTransactionsByUserId(@RequestParam(name="userId") String userId) {
        return transactionService.getTransactionsByUserId(userId);
    }

    // get transactions by date interval
    //@GetMapping
    //public ResponseEntity getTransactionsByDateInterval

    // get transactions by originProduct
    @GetMapping("/byOriginProduct")
    public ResponseEntity getTransactionsByOriginProduct(@RequestParam(name="originProduct") String originProduct) {
        return transactionService.getTransactionsByOriginProduct(originProduct);
    }

    // get transactions by receiver
    @GetMapping("/byDestinationProduct")
    public ResponseEntity getTransactionsByDestinationProduct(@RequestParam(name="destinationProduct") String destinationProduct) {
        return transactionService.getTransactionsByDestinationProduct(destinationProduct);
    }

    // submit transaction
    @PostMapping
    public ResponseEntity<TransactionDTO> submitTransaction(@RequestBody TransactionDTO transactionDto) {
        return transactionService.submitTransaction(transactionDto);
    }
}
