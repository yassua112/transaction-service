package com.transaction.stroreservice.model.response;

import java.util.List;

import com.transaction.stroreservice.model.dto.CompanyDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListCompanyResponse {
    private List<CompanyDetail> company;
}
