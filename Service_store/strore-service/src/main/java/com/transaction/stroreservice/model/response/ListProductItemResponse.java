package com.transaction.stroreservice.model.response;

import java.util.List;

import com.transaction.stroreservice.model.dto.ProductDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListProductItemResponse {

    private List<ProductDetail> product;
}
