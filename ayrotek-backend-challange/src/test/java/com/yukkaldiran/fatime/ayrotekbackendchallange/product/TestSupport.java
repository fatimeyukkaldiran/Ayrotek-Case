package com.yukkaldiran.fatime.ayrotekbackendchallange.product;

import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.ProductDto;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.request.CreateProductRequest;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.request.UpdateProductRequest;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.entity.Product;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TestSupport {

    public CreateProductRequest generateProductRequest(){
        return new CreateProductRequest(
                "name",
                5,
                BigDecimal.valueOf(1245.09876),
                BigDecimal.valueOf(12.45)
        );
    }

    public Product generateProduct(@Nullable Long id, CreateProductRequest productRequest){
         return new Product(
                    id,
                 productRequest.getName(),
                 productRequest.getQuantity(),
                 productRequest.getPrice(),
                 productRequest.getTax(),
                 LocalDateTime.of(2022, 07, 24, 0, 0),
                 LocalDateTime.of(2022, 07, 24, 0, 0)
        );
    }

    public ProductDto generateProductDto() {
        return new ProductDto(
                1L,
                "name",
                5,
                BigDecimal.valueOf(1245.09876),
                BigDecimal.valueOf(12.45),
                LocalDateTime.of(2022, 07, 24, 0, 0),
                LocalDateTime.of(2022, 07, 24, 0, 0)
        );
    }
    public Product generateProduct2(){
            return new Product(
                    1L,
                    "name",
                    5,
                    BigDecimal.valueOf(1245.09876),
                    BigDecimal.valueOf(12.45),
                    LocalDateTime.of(2022, 07, 24, 0, 0),
                    LocalDateTime.of(2022, 07, 24, 0, 0)
            );

        }
    public List<Product> generateProductList(){
        Product product = generateProduct2();
        return List.of(product);
    }

    public List<ProductDto> generateProductDtoList(){
        ProductDto product = generateProductDto();
        return List.of(product);
    }

    public UpdateProductRequest generateUpdateProductRequest(){
        return new UpdateProductRequest(
                "name name",
                6,
                BigDecimal.valueOf(1445.05876),
                BigDecimal.valueOf(12.45)
                );
    }

    public Product generateUpdatedProduct(Product product, UpdateProductRequest request){
        return new Product(
                product.getId(),
                request.getName(),
                request.getQuantity(),
                request.getPrice(),
                request.getTax(),
                product.getCreatedDate(),
                LocalDateTime.of(2022, 07, 26, 0, 0)

        );
    }

public ProductDto generateUpdatedProductDto(Product product){
        return new ProductDto(
             product.getId(),
             product.getName(),
             product.getQuantity(),
             product.getPrice(),
             product.getTax(),
             product.getCreatedDate(),
             product.getUpdatedDate()
        );
}
}
