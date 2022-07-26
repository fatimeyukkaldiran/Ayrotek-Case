package com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private  Long id;

    private String name;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal tax;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
