package com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.converter;

import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.ProductDto;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.request.UpdateProductRequest;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDtoConverter {

    public ProductDto convertToProductDto(Product from){
        return new ProductDto(
                from.getId(),
                from.getName(),
                from.getQuantity(),
                from.getPrice(),
                from.getTax(),
                from.getCreatedDate(),
                from.getUpdatedDate()
        );
    }

    public List<ProductDto> convertToProductDtoList(List<Product> products){
        return products
                .stream()
                .map(this::convertToProductDto)
                .collect(Collectors.toList());
    }
}
