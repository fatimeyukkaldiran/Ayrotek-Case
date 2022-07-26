package com.yukkaldiran.fatime.ayrotekbackendchallange.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRODUCT")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(generator = "Product")
    @SequenceGenerator(name = "Product",sequenceName = "PRODUCT_ID_SEQ")
    private  Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name="quantity_per_unit")
    private Integer quantity;

    @Column(name="unit_price")
    private BigDecimal price;

    @Column(name = "tax_rate")
    private BigDecimal tax;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    @JsonIgnore
    private LocalDateTime createdDate = LocalDateTime.now() ;

    @LastModifiedDate
    @Column(name = "updated_date", nullable = false)
    @JsonIgnore
    private LocalDateTime updatedDate = LocalDateTime.now();

}
