package com.yukkaldiran.fatime.ayrotekbackendchallange.product.service;

import com.yukkaldiran.fatime.ayrotekbackendchallange.product.exception.ProductNotFoundException;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.ProductDto;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.converter.ProductDtoConverter;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.mapper.ProductMapper;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.request.CreateProductRequest;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.request.ProductTaxRequest;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.request.UpdateProductRequest;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.entity.Product;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.repository.ProductRepository;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.utils.ErrorMessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductDtoConverter productDtoConverter;
    private final ProductMapper productMapper ;

    public ProductDto createProduct(CreateProductRequest productRequest){
        Product product = productMapper.convertToProduct(productRequest);

        return productDtoConverter.convertToProductDto(productRepository.save(product));
    }

    public List<ProductDto> getAllProducts(){
        return productDtoConverter.convertToProductDtoList(productRepository.findAll());
    }

    public ProductDto updateProduct(Long productId, UpdateProductRequest updateProductRequest){
       Product product = findProductById(productId);

       Product updatedProduct = new Product(
               product.getId(),
               updateProductRequest.getName(),
               updateProductRequest.getQuantity(),
               updateProductRequest.getPrice(),
               updateProductRequest.getTax(),
               product.getCreatedDate(),
               LocalDateTime.now()
       );

       return productDtoConverter.convertToProductDto(productRepository.save(updatedProduct));
    }

    public ProductDto getProductById(Long productId){
        return productMapper.convertToProductDto(findProductById(productId));
    }

    public String deleteProduct(Long productId){
        productRepository.delete(findProductById(productId));

        return productId + " deleted ...";
    }

    public ProductTaxRequest getTaxOfProduct(Long productId) {
      Product product = findProductById(productId);

        ProductTaxRequest taxRequest = new ProductTaxRequest(
                product.getTax(),
                product.getPrice()
        );

        return taxRequest;
    }

    protected Product findProductById(Long productId){
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(ErrorMessageConstants.PRODUCT_NOT_FOUND));
    }
}
