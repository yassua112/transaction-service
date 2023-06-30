package com.transaction.stroreservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Company")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEntity extends BaseEntity {

    @Id
    @Column(name = "Company_id", nullable = false)
    private String companyId;

    @Column(name = "Company_Code", nullable = false, unique = true)
    private String companyCode;

    @Column(name = "Company_Name", nullable = false)
    private String companyName;
}
