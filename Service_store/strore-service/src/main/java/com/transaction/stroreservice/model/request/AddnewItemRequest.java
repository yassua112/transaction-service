package com.transaction.stroreservice.model.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddnewItemRequest {

    private String itemCode;
    private String itemName;
    private BigDecimal itemPrice;
    private Integer itemStock;

}
