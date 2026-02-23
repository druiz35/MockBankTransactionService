package com.druiz.mockbanktransactionservice.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTO {
    public String id;
    public String originProduct;
    public String originProductType; // TODO: Use an Enum here...
    public String destinationProduct;
    public String destinationProductType; // TODO: Use an Enum here...
    public String dateTime; // TODO: Maybe use a LocalTime type...
    public float amountWithoutAppliedFee;
    public float amountWithAppliedFee;
    public float appliedFee;
    public String type; // TODO: Use an Enum here...
}
