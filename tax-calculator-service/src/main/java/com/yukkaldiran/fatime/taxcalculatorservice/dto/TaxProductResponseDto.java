package com.yukkaldiran.fatime.taxcalculatorservice.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class TaxProductResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private BigDecimal unitPrice;

    private BigDecimal taxRate;

    private BigDecimal taxOfProduct;


    public TaxProductResponseDto() {
    }

    public TaxProductResponseDto(BigDecimal unitPrice, BigDecimal taxRate, BigDecimal taxOfProduct) {
        this.unitPrice = unitPrice;
        this.taxRate = taxRate;
        this.taxOfProduct = taxOfProduct;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxOfProduct() {
        return taxOfProduct;
    }

    public void setTaxOfProduct(BigDecimal taxOfProduct) {
        this.taxOfProduct = taxOfProduct;
    }
}
