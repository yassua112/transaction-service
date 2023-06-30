package com.transaction.stroreservice.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transaction.stroreservice.model.dto.ProductDetail;
import com.transaction.stroreservice.model.entity.ProductItemEntity;
import com.transaction.stroreservice.model.request.AddnewItemRequest;
import com.transaction.stroreservice.model.response.BaseResponse;
import com.transaction.stroreservice.model.response.ListProductItemResponse;
import com.transaction.stroreservice.repository.ProductItemRepository;
import com.transaction.stroreservice.utils.CustomException;

@Service
public class ProductItemService {

    @Autowired
    ProductItemRepository productItemRepository;



    public ListProductItemResponse getProduct(){
        List<ProductItemEntity> data = productItemRepository.findAll();
        var dataList = data.stream().map(this::toResponse).collect(Collectors.toList());
        return ListProductItemResponse.builder().product(dataList).build();
    }

    private ProductDetail toResponse(ProductItemEntity dataEntity){
        return ProductDetail.builder().productCode(dataEntity.getProductId()).productName(dataEntity.getProductName()).productPrice(dataEntity.getProductPrice()).productStock(dataEntity.getProductStoct()).build();
    }


    public BaseResponse createdNewProduct(AddnewItemRequest request){
        var productDetail = productItemRepository.findByProductCode(request.getItemCode());
        if(productDetail.isPresent()){
            throw new CustomException("Code Product Sudah Terdaftar");
        }
        ProductItemEntity product = new ProductItemEntity();
        product.setProductId(UUID.randomUUID().toString());
        product.setProductCode(request.getItemCode());
        product.setProductName(request.getItemName());
        product.setProductPrice(request.getItemPrice());
        product.setProductStoct(request.getItemStock());
        productItemRepository.save(product);
        return BaseResponse.builder().mssg("Data Product Berhasil Di Simpan").status("Success").addType("add Product").code("200").build();

    }

    public BaseResponse updateProductDetail(AddnewItemRequest request){
        var productDetail = productItemRepository.findByProductCode(request.getItemCode());
         if(!productDetail.isPresent()){
            throw new CustomException("Gagal Update, Product belum Terdaftar");
        }
        var productEntity = productDetail.get();
        productEntity.setProductName(request.getItemName());
        productEntity.setProductPrice(request.getItemPrice());
        productEntity.setProductStoct(request.getItemStock());
        productItemRepository.save(productEntity);

        return BaseResponse.builder().mssg("Data Product Berhasil Di Update").status("Success").addType("Update Product").code("200").build();

    }


}
