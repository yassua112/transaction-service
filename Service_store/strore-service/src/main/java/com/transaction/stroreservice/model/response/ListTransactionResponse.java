package com.transaction.stroreservice.model.response;

import java.util.List;

import com.transaction.stroreservice.model.dto.TransactionDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListTransactionResponse {
    private List<TransactionDetail> transactionList;
    
}
