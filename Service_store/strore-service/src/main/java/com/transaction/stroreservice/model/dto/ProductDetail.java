package com.transaction.stroreservice.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProductDetail {
    
    private String productCode;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
}
