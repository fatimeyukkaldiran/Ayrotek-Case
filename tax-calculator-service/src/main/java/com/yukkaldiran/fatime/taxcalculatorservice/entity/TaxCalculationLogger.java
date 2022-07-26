package com.yukkaldiran.fatime.taxcalculatorservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class TaxCalculationLogger {
    @Id
    private Long id;
    private BigDecimal taxRate;
    private BigDecimal price;
    private BigDecimal taxOfProduct;

}
