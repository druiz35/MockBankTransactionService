package com.druiz.mockbanktransactionservice.model;

import com.druiz.mockbanktransactionservice.enums.ProductTypeEnum;
import com.druiz.mockbanktransactionservice.enums.TransactionTypeEnum;
import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.time.Instant;
import java.util.UUID;

@DynamoDbBean
@Getter
@Setter
public class Transaction {
    private UUID id;
    private UUID originProductID;
    private ProductTypeEnum originProductType;
    private UUID destinationProductID;
    private ProductTypeEnum destinationProductType;
    private Instant timestamp;
    private float amountWithoutAppliedFee;
    private float amountWithAppliedFee;
    private float appliedFee;
    private TransactionTypeEnum type;

    @DynamoDbPartitionKey
    public UUID getId() {
        return id;
    }

}
