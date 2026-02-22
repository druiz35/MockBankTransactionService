package com.druiz.mockbanktransactionservice.dto;


import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.UUID;

@Getter
@Setter
@DynamoDbBean
public class TransactionDTO {
    public UUID id;
    public String accountId;
    public double amount;
    public String currency; // Maybe use an enum here
    public String timestamp; // Maybe use a datetime type...
    public String userId; // Here I'll need to use a foreignKey for the user

    @DynamoDbPartitionKey
    public UUID getId() {
        return id;
    }
}

