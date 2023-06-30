package com.transaction.stroreservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDetail {
    private String companyCode;
    private String companyId;
    private String companyName;
    
}
