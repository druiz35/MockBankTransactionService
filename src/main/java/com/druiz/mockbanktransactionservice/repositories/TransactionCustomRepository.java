package com.druiz.mockbanktransactionservice.repositories;

import com.druiz.mockbanktransactionservice.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TransactionCustomRepository {
    private final DynamoDbTable<Transaction> transactionDynamoDbTableTable;

    // TODO: Create transaction. I need to validate as well...
    public Transaction submitTransaction(Transaction transaction) {
        transactionDynamoDbTableTable.putItem(transaction);
        return transaction;
    }

    // TODO: get transaction by userId. Agregar campo de UserID
    // TODO: Desacoplar en un manejador de filtros custom
    public List<Transaction> getByUserId(String userId) {
        List<Transaction> transactions = new ArrayList<>();
        Map<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":val", AttributeValue.builder().b(SdkBytes.fromUtf8String(userId)).build());

        Expression filterExpression = Expression.builder()
                .expression("userId = :val")
                .expressionValues(expressionValues)
                .build();
        transactionDynamoDbTableTable.scan(trans -> trans.filterExpression(filterExpression))
                .items()
                .forEach(transactions::add);
        return transactions;
    }

    //  TODO: get transaction status Esto es para después. No voy a lidiar con status de transacciones aún

    // TODO: get transactions by date interval - quiero pasar local datetime para los intervalos e implementar una
    //       lógica que haga el filtrado de la forma más óptima...
    //public List<Transaction> getByDateInterval(LocalDateTime leftBoundDate, LocalDateTime rightBoundDate){};

    // get transactions by sender
    public List<Transaction> getByOriginProduct(String originProduct) {
        // TODO: Desacoplar en un manejador de filtros custom
        List<Transaction> transactions = new ArrayList<>();
        Map<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":originProduct", AttributeValue.builder().b(SdkBytes.fromUtf8String(originProduct)).build());

        Expression filterExpression = Expression.builder()
                .expression("originProduct = :originProduct")
                .expressionValues(expressionValues)
                .build();

        // TODO: IMPLEMENTAR CON ENHANCED FILTERING


        transactionDynamoDbTableTable.scan(trans -> trans.filterExpression(filterExpression))
                .items()
                .forEach(transactions::add);
        return transactions;
    }

    // get transactions by receiver
    public List<Transaction> getByDestinationProduct(String destinationProduct) {
        // TODO: Desacoplar en un manejador de filtros custom
        List<Transaction> transactions = new ArrayList<>();
        Map<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":destinationProduct", AttributeValue.builder().b(SdkBytes.fromUtf8String(destinationProduct)).build());

        Expression filterExpression = Expression.builder()
                .expression("destinationProduct = :destinationProduct")
                .expressionValues(expressionValues)
                .build();
        transactionDynamoDbTableTable.scan(trans -> trans.filterExpression(filterExpression))
                .items()
                .forEach(transactions::add);
        return transactions;
    }

    // Operations for employees, admin, and other services
    public List<Transaction> getAll() {
        List<Transaction> transactions = new ArrayList<>();
        transactionDynamoDbTableTable.scan().items().forEach(transactions::add);
        return transactions;
    }


}
