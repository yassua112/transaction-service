package com.transaction.stroreservice.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transaction.stroreservice.model.dto.CompanyDetail;
import com.transaction.stroreservice.model.entity.CompanyEntity;
import com.transaction.stroreservice.model.request.AddnewItemRequest;
import com.transaction.stroreservice.model.request.CreateNewCompanyRequest;
import com.transaction.stroreservice.model.response.BaseResponse;
import com.transaction.stroreservice.model.response.ListCompanyResponse;
import com.transaction.stroreservice.repository.CompanyRepository;
import com.transaction.stroreservice.utils.CustomException;


@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    public ListCompanyResponse getCompanyList(){
        List<CompanyEntity> dataCompany = companyRepository.findAll();
        var listData = dataCompany.stream().map(this::toResponse).collect(Collectors.toList());
        return ListCompanyResponse.builder().company(listData).build();
    }



    private CompanyDetail toResponse(CompanyEntity companyEntity){
        return CompanyDetail.builder().companyId(companyEntity.getCompanyId()).companyName(companyEntity.getCompanyName()).companyCode(companyEntity.getCompanyCode()).build();
    }

    public BaseResponse createdNewCompany(CreateNewCompanyRequest request){
        var commpayCheck = companyRepository.findByCompanyCode(request.getCompanyCode());
        if(commpayCheck.isPresent()){
            throw new CustomException("Code Company Sudah Terdaftar");
        }
        CompanyEntity commpany = new CompanyEntity();
        commpany.setCompanyId(UUID.randomUUID().toString());
        commpany.setCompanyCode(request.getCompanyCode());
        commpany.setCompanyName(request.getCompanyName());
        companyRepository.save(commpany);
        return BaseResponse.builder().mssg("Data Company Berhasil Di Simpan").status("Success").addType("add Company").code("200").build();

    }


    public BaseResponse updateCompanyDetail(CreateNewCompanyRequest request){
        var companyCheck = companyRepository.findByCompanyCode(request.getCompanyCode());
         if(!companyCheck.isPresent()){
            throw new CustomException("Gagal Update, Company belum Terdaftar");
        }
        var companyEntity = companyCheck.get();
        companyEntity.setCompanyName(request.getCompanyName());
        companyRepository.save(companyEntity);
        return BaseResponse.builder().mssg("Data Company Berhasil Di Update").status("Success").addType("Update Company").code("200").build();

    }


}
