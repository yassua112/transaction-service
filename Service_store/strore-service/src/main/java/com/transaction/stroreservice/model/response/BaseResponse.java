package com.transaction.stroreservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BaseResponse {
    private String mssg;
    private String status;
    private String code;
    private String addType;
    
}
