package com.transaction.stroreservice.model.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionAddResponse {
    
    private String transactionId;
    private String itemId;
    private String itemName;
    private String transactiontype;
    
}
