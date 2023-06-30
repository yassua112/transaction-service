package com.transaction.stroreservice.model.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetail {
    private String id;
    private String transactionId;
    private String companyName;
    private String productItemName;
    private Integer productAmount;
    private BigDecimal priceAmount;
    private String transactionType;
    private Timestamp transactionDate;
    
}
