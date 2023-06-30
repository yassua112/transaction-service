package com.transaction.stroreservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.transaction.stroreservice.model.request.TransactionAddRequest;
import com.transaction.stroreservice.model.response.ListTransactionResponse;
import com.transaction.stroreservice.model.response.TransactionAddResponse;
import com.transaction.stroreservice.service.TransactionService;

@RestController
@RequestMapping("/transaction/")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping(value = ("add"), produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionAddResponse postAddnewItem(@RequestBody TransactionAddRequest request) {

        return transactionService.addNewTransaction(request);

    }

    
    @GetMapping(value = "get")
    public ListTransactionResponse getTransactionDetail(){
        return transactionService.getListTransaction();
    }

}
