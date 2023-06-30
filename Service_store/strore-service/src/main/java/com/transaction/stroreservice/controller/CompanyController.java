package com.transaction.stroreservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.stroreservice.model.request.CreateNewCompanyRequest;
import com.transaction.stroreservice.model.response.BaseResponse;

import com.transaction.stroreservice.model.response.ListCompanyResponse;

import com.transaction.stroreservice.service.CompanyService;

@RestController
@RequestMapping("/company/")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping(value = "create")
    public BaseResponse createNewCompany(@RequestBody CreateNewCompanyRequest request){
        return companyService.createdNewCompany(request);
    }


    @GetMapping(value = "get")
    public ListCompanyResponse getCompany(){
        return companyService.getCompanyList();
    }

    @PostMapping(value = "update")
    public BaseResponse updateCompany(@RequestBody CreateNewCompanyRequest request){
        return companyService.updateCompanyDetail(request);
    }
 }
