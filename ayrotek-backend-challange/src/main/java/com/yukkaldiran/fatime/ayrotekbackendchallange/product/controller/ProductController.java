package com.yukkaldiran.fatime.ayrotekbackendchallange.product.controller;

import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.ProductDto;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.TaxProductResponse;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.request.CreateProductRequest;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.request.ProductTaxRequest;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.request.UpdateProductRequest;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final RestTemplate restTemplate;

    private final String taxCalculateUrl = "http://localhost:8082/api/tax-calculate" ;

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProductRequest(@Valid @RequestBody CreateProductRequest productRequest){
       ProductDto productDto = productService.createProduct(productRequest);

       return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ProductDto> updateProduct(@RequestParam Long productId, UpdateProductRequest updateProductRequest){
         ProductDto productDto = productService.updateProduct(productId, updateProductRequest);

        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<ProductDto> productDtoList = productService.getAllProducts();

      return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @PostMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId){
        ProductDto productDto = productService.getProductById(productId);

        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId){
        String result = productService.deleteProduct(productId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("tax-of-product/{productId}")
    public ResponseEntity<TaxProductResponse> getProductTax(@PathVariable Long productId) {

       ProductTaxRequest taxRequest = productService.getTaxOfProduct(productId);
       TaxProductResponse result = restTemplate.postForObject(taxCalculateUrl, taxRequest, TaxProductResponse.class);

       return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
