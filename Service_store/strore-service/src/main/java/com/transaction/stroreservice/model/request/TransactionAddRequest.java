package com.transaction.stroreservice.model.request;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionAddRequest {
 
    private String companyName;
    private String companyCode;
    private String productName;
    private String productCode;
    private BigInteger priceAmount;
    private Integer productAmount;
    private String transactionType;
    private String transactionId;
    
}
