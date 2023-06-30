package com.transaction.stroreservice.model.enums;

public enum TransactionStatusEnum {
    BUY("BUY"),RETURN("RETURN");
    
    private final String name;

    private TransactionStatusEnum(String value){
        this.name = value;
    }

    public String value() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }

}
