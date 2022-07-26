package com.yukkaldiran.fatime.taxcalculatorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxCalculateRequestDto {

    private BigDecimal taxRate;
    private BigDecimal price;
}
