package com.druiz.mockbanktransactionservice.repositories;

import com.druiz.mockbanktransactionservice.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TransactionCustomRepository {
    @Value("${dynamodb.tableName}")
    private String tableName;

    private final DynamoDbEnhancedClient dynamoDbClient;
    private final DynamoDbTable<TransactionDTO> transactionTable;

    public TransactionCustomRepository(DynamoDbEnhancedClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
        this.transactionTable = dynamoDbClient.table(
                this.tableName,
                TableSchema.fromBean(TransactionDTO.class)
        );
    }

    public TransactionDTO submitTransaction(TransactionDTO transaction) {
        // TODO: Create transaction. I need to validate as well...
        transactionTable.putItem(transaction);
        return transaction;
    }

    // get transaction by userId
    public List<TransactionDTO> getByUserId(String userId) {
        // TODO: Desacoplar en un manejador de filtros custom
        List<TransactionDTO> transactions = new ArrayList<>();
        Map<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":val", AttributeValue.builder().s(userId).build());

        Expression filterExpression = Expression.builder()
                .expression("userId = :val")
                .expressionValues(expressionValues)
                .build();
        transactionTable.scan(trans -> trans.filterExpression(filterExpression))
                .items()
                .forEach(transactions::add);
        return transactions;
    }

    // OPERATIONS FOR CLIENTS
    // get transaction status TODO: Esto es para después. No voy a lidiar con status de transacciones aún

    // TODO: get transactions by date interval - quiero pasar local datetime para los intervalos e implementar una
    //       lógica que haga el filtrado de la forma más óptima...
    /*
    public List<TransactionDTO> getByDateInterval() {

    }
    */

    // get transactions by sender
    public List<TransactionDTO> getByOriginProduct(String originProduct) {
        // TODO: Desacoplar en un manejador de filtros custom
        List<TransactionDTO> transactions = new ArrayList<>();
        Map<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":originProduct", AttributeValue.builder().s(originProduct).build());


        Expression filterExpression = Expression.builder()
                .expression("originProduct = :originProduct")
                .expressionValues(expressionValues)
                .build();

        // TODO: IMPLEMENTAR CON ENHANCED FILTERING


        transactionTable.scan(trans -> trans.filterExpression(filterExpression))
                .items()
                .forEach(transactions::add);
        return transactions;

    }

    // get transactions by receiver
    public List<TransactionDTO> getByDestinationProduct(String destinationProduct) {
        // TODO: Desacoplar en un manejador de filtros custom
        List<TransactionDTO> transactions = new ArrayList<>();
        Map<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":destinationProduct", AttributeValue.builder().s(destinationProduct).build());

        Expression filterExpression = Expression.builder()
                .expression("destinationProduct = :destinationProduct")
                .expressionValues(expressionValues)
                .build();
        transactionTable.scan(trans -> trans.filterExpression(filterExpression))
                .items()
                .forEach(transactions::add);
        return transactions;
    }

    // Operations for employees, admin, and other services
    public List<TransactionDTO> getAll() {
        List<TransactionDTO> transactions = new ArrayList<>();
        transactionTable.scan().items().forEach(transactions::add);
        return transactions;
    }


}
