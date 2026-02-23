package com.druiz.mockbanktransactionservice.model;

//import com.druiz.mockbanktransactionservice.enums.ProductTypeEnum;
//import com.druiz.mockbanktransactionservice.enums.TransactionTypeEnum;
import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@DynamoDbBean
public class Transaction {
    private byte[] id;
    private byte[] originProduct;
    //private ProductTypeEnum originProductType;
    private String originProductType;
    private byte[] destinationProduct;
    //private ProductTypeEnum destinationProductType;
    private String destinationProductType;
    private LocalDateTime dateTime;
    private float amountWithoutAppliedFee;
    private float amountWithAppliedFee;
    private float appliedFee;
    //private TransactionTypeEnum type;
    private String type;

    @DynamoDbPartitionKey
    public byte[] getId() {
        return id;
    }

    public void setIdFromUUID(UUID uuid) {
        this.id = uuid.toString().getBytes();
    }

}
