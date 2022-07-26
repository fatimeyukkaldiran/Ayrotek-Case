package com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.mapper;

import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.ProductDto;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.request.CreateProductRequest;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product convertToProduct(CreateProductRequest productRequest);

    ProductDto convertToProductDto(Product product);
}
