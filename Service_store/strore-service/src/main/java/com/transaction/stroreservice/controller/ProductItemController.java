package com.transaction.stroreservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.stroreservice.model.request.AddnewItemRequest;
import com.transaction.stroreservice.model.request.CreateNewCompanyRequest;
import com.transaction.stroreservice.model.response.BaseResponse;
import com.transaction.stroreservice.model.response.ListCompanyResponse;
import com.transaction.stroreservice.model.response.ListProductItemResponse;
import com.transaction.stroreservice.service.CompanyService;
import com.transaction.stroreservice.service.ProductItemService;

@RestController
@RequestMapping("/product/")
public class ProductItemController {
    

    @Autowired
    ProductItemService productItemService;

    @PostMapping(value = "create")
    public BaseResponse createNewCompany(@RequestBody AddnewItemRequest request){
        return productItemService.createdNewProduct(request);
    }


    @GetMapping(value = "get")
    public ListProductItemResponse getCompany(){
        return productItemService.getProduct();
    }

    @PostMapping(value = "update")
    public BaseResponse updateCompany(@RequestBody AddnewItemRequest request){
        return productItemService.updateProductDetail(request);
    }
    
}
