package com.druiz.banktransactionapi.services;

import com.druiz.banktransactionapi.dto.TransactionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.*;

@Service("transactionService")
public class TransactionService {
    private final DynamoDbEnhancedClient dynamoDbClient;
    private final DynamoDbTable<TransactionDTO> exampleTable;

    public TransactionService(DynamoDbEnhancedClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
        this.exampleTable = dynamoDbClient.table(
                "ejemplo",
                TableSchema.fromBean(TransactionDTO.class)
        );
    }

    public ResponseEntity<TransactionDTO> saveTransaction(TransactionDTO transactionDto) {
        transactionDto.id = UUID.randomUUID();
        exampleTable.putItem(transactionDto);
        return new ResponseEntity<>(transactionDto, HttpStatus.OK);
    }

    public ResponseEntity getAllTransactions() {
        List<TransactionDTO> transactions = new ArrayList<>();
        exampleTable.scan().items().forEach(transactions::add);
        return new ResponseEntity(transactions, HttpStatus.OK);
    }

    public ResponseEntity getTransactionsByUserId(String userId) {
        List<TransactionDTO> transactions = new ArrayList<>();
        Map<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":val", AttributeValue.builder().s(userId).build());

        Expression filterExpression = Expression.builder()
                .expression("userId = :val")
                .expressionValues(expressionValues)
                .build();
        exampleTable.scan(r -> r.filterExpression(filterExpression))
                .items()
                .forEach(transactions::add);
        return new ResponseEntity(transactions, HttpStatus.OK);
    }
}
