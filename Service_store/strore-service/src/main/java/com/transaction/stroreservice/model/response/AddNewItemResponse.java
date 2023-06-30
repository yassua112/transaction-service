package com.transaction.stroreservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddNewItemResponse {
    private String itemId;
    private String itemName;
    private String itemPrice;
    private String itemStoc;
}
