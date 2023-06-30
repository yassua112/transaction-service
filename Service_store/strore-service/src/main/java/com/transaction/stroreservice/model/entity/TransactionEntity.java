package com.transaction.stroreservice.model.entity;

import java.math.BigDecimal;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
@Table(name = "Transaction")
public class TransactionEntity extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "transaction_id", nullable = false)
    private String transactionId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_code")
    private String companyCode;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "prodct_name")
    private String productCode;

    @Column(name = "price_Amount")
    private BigDecimal priceAmount;

    @Column(name = "product_Amount")
    private Integer productAmount;

    @Column(name= "transaction_status")
    private String transactionType;
}
