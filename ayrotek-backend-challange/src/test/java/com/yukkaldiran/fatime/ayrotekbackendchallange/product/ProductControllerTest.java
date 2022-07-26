package com.yukkaldiran.fatime.ayrotekbackendchallange.product;

import com.yukkaldiran.fatime.ayrotekbackendchallange.product.controller.ProductController;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.ProductDto;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.TaxProductResponse;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.request.ProductTaxRequest;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.request.UpdateProductRequest;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest extends TestSupport{
    private final String taxCalculateUrl = "http://localhost:8082/api/tax-calculate" ;

    private ProductService productService;
    private RestTemplate restTemplate;
    private ProductController productController;
    @BeforeEach
    public void setUp() {
        productService = mock(ProductService.class);
        restTemplate = mock(RestTemplate.class);
        productController = new ProductController(productService,restTemplate);
    }

    @Test
    public void testGetAllProduct_shouldReturnProductList(){
        List<ProductDto> expected = new ArrayList<>();
        when(productService.getAllProducts()).thenReturn(expected);

        ResponseEntity<List<ProductDto>> result = productController.getProducts();
        List<ProductDto> actual = result.getBody();

        assertAll(
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    public void testUpdateProduct_when_updateProduct_called() {
        Long id = 1L;
        UpdateProductRequest request = generateUpdateProductRequest();
        ProductDto expected =new ProductDto();
        when(productService.updateProduct(id, request)).thenReturn(expected);

        ResponseEntity<ProductDto> result = productController.updateProduct(id,request);
        ProductDto actual = result.getBody();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );

    }

    @Test
    public void testSaveProduct_when_createProductRequest_called() {
        ProductDto expected = new ProductDto();

        when(productService.createProduct(any())).thenReturn(expected);

        ResponseEntity<ProductDto> result = productController.createProductRequest(any());
        ProductDto actual = result.getBody();

        assertAll(
                () -> assertEquals(expected, actual)
        );

    }
    @Test
    void testDeleteProduct_when_deleteProduct_called() {
        ProductDto expected = new ProductDto();

        when(productService.deleteProduct(expected.getId())).thenReturn("1 deleted...");

        ResponseEntity<?> result = productController.deleteProduct(expected.getId());


        assertAll(
                () -> assertNotNull(result)
        );
    }

    @Test
    public void testFindByIdCustomer_shouldReturnProductDto(){

        ProductDto expected = generateProductDto();

        when(productService.getProductById(1L)).thenReturn(expected);

        ProductDto actual = productController.getProduct(1L).getBody();

        assertAll(
                () -> assertEquals(actual,expected)
        );

        verify(productService).getProductById(1L);
    }

    @Test
    public void testGetProductTax(){
        ProductTaxRequest request = new ProductTaxRequest(BigDecimal.valueOf(12.45), BigDecimal.valueOf(1245.09876));
        BigDecimal tax = request.getPrice().multiply(request.getTaxRate());
        TaxProductResponse expected = new TaxProductResponse(request.getPrice(),request.getTaxRate(), tax);

        when(productService.getTaxOfProduct(1L)).thenReturn(request);
        when(restTemplate.postForObject(taxCalculateUrl,request, TaxProductResponse.class)).thenReturn(expected);
        ResponseEntity<TaxProductResponse> result = productController.getProductTax(1L);

        TaxProductResponse actual = result.getBody();

        assertAll(
                () -> assertEquals(actual, expected)
        );
    }
}
