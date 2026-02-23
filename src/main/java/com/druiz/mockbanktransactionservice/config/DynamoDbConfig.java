package com.druiz.mockbanktransactionservice.config;

import com.druiz.mockbanktransactionservice.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;


@Configuration
public class DynamoDbConfig {
    @Bean
    public DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.builder().build();
    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
        return DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient).build();
    }

    @Bean
    public DynamoDbTable<Transaction> transactionDynamoDbTable(DynamoDbEnhancedClient dynamoDbEnhancedClient, @Value("${dynamodb.tableName:transactions}") String tableName) {
        return dynamoDbEnhancedClient.table(
                tableName,
                TableSchema.fromBean(Transaction.class)
        );
    }
}
