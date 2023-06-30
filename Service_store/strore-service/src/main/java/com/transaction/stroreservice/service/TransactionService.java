package com.transaction.stroreservice.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transaction.stroreservice.model.dto.TransactionDetail;
import com.transaction.stroreservice.model.entity.ProductItemEntity;
import com.transaction.stroreservice.model.entity.TransactionEntity;
import com.transaction.stroreservice.model.enums.TransactionStatusEnum;
import com.transaction.stroreservice.model.request.TransactionAddRequest;
import com.transaction.stroreservice.model.response.ListTransactionResponse;
import com.transaction.stroreservice.model.response.TransactionAddResponse;
import com.transaction.stroreservice.repository.ProductItemRepository;
import com.transaction.stroreservice.repository.TransactionRepository;
import com.transaction.stroreservice.utils.CustomException;

@Service
public class TransactionService{

    @Autowired
    ProductItemRepository productItemRepository;

    @Autowired
    TransactionRepository transactionRepository;

    
    public TransactionAddResponse addNewTransaction(TransactionAddRequest request){
        verifyTransactionType(request.getTransactionType());
        if(request.getTransactionType().equalsIgnoreCase(TransactionStatusEnum.BUY.toString())){
            return addNewTransactionBuy(request);
        }
        return addNewTransactionReversal(request);
    }


    private BigDecimal sumTotalAmoutToPay(Integer producAmount , BigDecimal productPrice){
        return BigDecimal.valueOf(producAmount).multiply(productPrice);
    }

    private void verifyTransactionType(String transactionType){
        if(!transactionType.equalsIgnoreCase(TransactionStatusEnum.BUY.toString()) && !transactionType.equalsIgnoreCase(TransactionStatusEnum.RETURN.toString())){
            throw new CustomException("Transaction Tidak Valid");
        }
    }

    private TransactionAddResponse addNewTransactionBuy(TransactionAddRequest request){
        LocalDateTime currentDate = LocalDateTime.now();
        Timestamp date = Timestamp.valueOf(currentDate);
        var dataItem = productItemRepository.findByProductCode(request.getProductCode())
                .orElseThrow(() -> {throw new CustomException("Item Tidak Ditemukan");});
        if(dataItem.getProductStoct() == 0){
            throw new CustomException("Stoct Item untuk Barang " + dataItem.getProductName()+"Sedang Kosong");
        }
        Integer stoctItem = dataItem.getProductStoct();
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setId(UUID.randomUUID().toString());
        transactionEntity.setTransactionId(UUID.randomUUID().toString());
        transactionEntity.setCompanyCode(request.getCompanyCode());
        transactionEntity.setCompanyName(request.getCompanyName());
        transactionEntity.setProductCode(request.getProductCode());
        transactionEntity.setProductName(request.getProductName());
        transactionEntity.setProductAmount(request.getProductAmount());
        transactionEntity.setPriceAmount(sumTotalAmoutToPay(request.getProductAmount(),dataItem.getProductPrice()));
        transactionEntity.setTransactionType(request.getTransactionType());
        transactionEntity.setCreateBy("KASIR");
        transactionEntity.setCreatedDate(date);
        transactionRepository.save(transactionEntity);

        if(stoctItem - request.getProductAmount() < 0 ){
        dataItem.setProductStoct(0);
        }else{
             dataItem.setProductStoct(stoctItem - request.getProductAmount());
        }
        productItemRepository.save(dataItem);
        

        return TransactionAddResponse.builder().itemId(transactionEntity.getProductCode())
                                               .transactionId(transactionEntity.getTransactionId())
                                               .itemName(transactionEntity.getProductName())
                                               .transactiontype(request.getTransactionType())
                                               .build();
    }

    private TransactionAddResponse addNewTransactionReversal(TransactionAddRequest request){
        LocalDateTime currentDate = LocalDateTime.now();
        Timestamp date = Timestamp.valueOf(currentDate);
        var dataItem = transactionRepository.findByTransactionIdAndTransactionType(request.getTransactionId(),TransactionStatusEnum.BUY.toString())
                .orElseThrow(() -> {throw new CustomException("Transaksi Tidak Ditemukan");});
        if(dataItem.getTransactionType().equalsIgnoreCase(TransactionStatusEnum.RETURN.toString())){
            throw new CustomException("Transaksi Ini Sudah di Reversal");
        }
       TransactionEntity dataItems = new TransactionEntity();
       dataItems.setId(UUID.randomUUID().toString());
       dataItems.setCompanyCode(request.getCompanyCode());
       dataItems.setCompanyName(request.getCompanyName());
       dataItems.setProductCode(request.getProductCode());
       dataItems.setProductName(request.getProductName());
       dataItems.setProductAmount(request.getProductAmount());
       dataItems.setTransactionId(request.getTransactionId());
       dataItems.setPriceAmount(dataItem.getPriceAmount().negate());
       dataItems.setTransactionType(TransactionStatusEnum.RETURN.toString());
       dataItems.setCreatedDate(date);
       transactionRepository.save(dataItems);
    
        return TransactionAddResponse.builder().itemId(dataItem.getProductCode())
                                               .transactionId(dataItem.getTransactionId())
                                               .itemName(dataItem.getProductName())
                                               .transactiontype(request.getTransactionType())
                                               .build();
    }

    public ListTransactionResponse getListTransaction(){
       List<TransactionEntity> data = transactionRepository.findAll();
       var listResponse = data.stream().map(this::toListResponse).collect(Collectors.toList());
        return ListTransactionResponse.builder().transactionList(listResponse).build();
    }


    private TransactionDetail toListResponse(TransactionEntity entityData){
        return TransactionDetail.builder().companyName(entityData.getCompanyName())
                                          .productItemName(entityData.getProductName())
                                          .productAmount(entityData.getProductAmount())
                                          .priceAmount(entityData.getPriceAmount())
                                          .transactionType(entityData.getTransactionType())
                                          .id(entityData.getId())
                                          .transactionId(entityData.getTransactionId())
                                          .transactionDate(entityData.getCreatedDate())
                                          .build();
    }
}
