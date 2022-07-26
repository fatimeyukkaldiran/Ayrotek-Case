package com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductTaxRequest {
    private BigDecimal taxRate;
    private BigDecimal price;
}
