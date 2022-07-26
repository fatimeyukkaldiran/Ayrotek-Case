package com.yukkaldiran.fatime.ayrotekbackendchallange.product.service;

import com.yukkaldiran.fatime.ayrotekbackendchallange.product.TestSupport;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.ProductDto;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.converter.ProductDtoConverter;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.mapper.ProductMapper;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.request.CreateProductRequest;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.dto.request.UpdateProductRequest;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.entity.Product;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.exception.ProductNotFoundException;
import com.yukkaldiran.fatime.ayrotekbackendchallange.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
 class ProductServiceTest extends TestSupport {

    private ProductRepository productRepository;
    private ProductDtoConverter productDtoConverter;
    private  ProductMapper productMapper;
    private ProductService productService;

    @BeforeEach
     void setUp(){
        productRepository = Mockito.mock(ProductRepository.class);
        productDtoConverter = Mockito.mock(ProductDtoConverter.class);
        productMapper = Mockito.mock(ProductMapper.class);
        productService = new ProductService(productRepository,productDtoConverter,productMapper);
    }

    @Test
   void testCreateProduct_whenCalledCreateProductRequest_shouldReturnProductDto(){
       CreateProductRequest productRequest = generateProductRequest();
        Product product =generateProduct(null,
                productRequest
                );

        Product expectedProduct = generateProduct(1L,
                productRequest);

        ProductDto expectedProductDto = generateProductDto();

        Mockito.when(productMapper.convertToProduct(productRequest)).thenReturn(product);
        Mockito.when(productRepository.save(product)).thenReturn(expectedProduct);
        Mockito.when(productMapper.convertToProductDto(expectedProduct)).thenReturn(expectedProductDto);

        ProductDto result = productService.createProduct(productRequest);
        assertEquals(expectedProductDto,result);

        Mockito.verify(productMapper).convertToProduct(productRequest);
        Mockito.verify(productRepository).save(product);
        Mockito.verify(productMapper).convertToProductDto(expectedProduct);

    }

    @Test
    void testGetProductById_whenProductIdIsExist_shouldReturnProductDto(){
        Product product = generateProduct2();
        ProductDto productDto = generateProductDto();

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Mockito.when(productMapper.convertToProductDto(product)).thenReturn(productDto);

        ProductDto actual = productService.getProductById(1L);

        assertEquals(productDto,actual);

        Mockito.verify(productRepository).findById(1L);
        Mockito.verify(productMapper).convertToProductDto(product);
    }
    @Test
    void testGetProductById_whenProductIdNotExist_shouldThrowProductNotFoundException(){
        Mockito.when(productRepository.findById(1L)).thenThrow(ProductNotFoundException.class);

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(1L));

        Mockito.verify(productRepository).findById(1L);
        Mockito.verifyNoInteractions(productMapper);
    }

    @Test
    void testGetAllProducts_shouldReturnProductDtoList(){
        List<Product> products = generateProductList();
        List<ProductDto> productDtoList = generateProductDtoList();

        Mockito.when(productRepository.findAll())
                .thenReturn(products);
        Mockito.when(productDtoConverter.convertToProductDtoList(products))
                .thenReturn(productDtoList);

        List<ProductDto> result = productService.getAllProducts();

        assertEquals(productDtoList, result);

        Mockito.verify(productRepository).findAll();
        Mockito.verify(productDtoConverter).convertToProductDtoList(products);
    }

    @Test
    void testDeleteProduct_whenCalledValidId_shouldReturnString() {
        Product product = generateProduct2();
        ProductDto productDto = generateProductDto();

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        String result = productService.deleteProduct(1L);

        assertEquals("1 deleted ...", result);

        Mockito.verify(productRepository).findById(1L);
    }

    @Test
    void testDeleteProduct_whenCalledInvalidId_itShouldThrowProductNotFoundException() {

        Mockito.when(productRepository.findById(1L)).thenThrow(ProductNotFoundException.class);

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(1L));

        Mockito.verify(productRepository).findById(1L);
        Mockito.verifyNoInteractions(productDtoConverter);
    }

    @Test
    void testUpdateProduct_whenCalledValidRequest_shouldReturnProductDto() {

        UpdateProductRequest request = generateUpdateProductRequest();
        Product updatedProduct = generateUpdatedProduct(generateProduct2(), request);
        ProductDto productDto = generateUpdatedProductDto(updatedProduct);

        Mockito.lenient().when(productRepository.findById(1L)).thenReturn(Optional.of(generateProduct2()));
        Mockito.lenient().when(productRepository.save(updatedProduct)).thenReturn(updatedProduct);
        Mockito.when(productMapper.convertToProductDto(updatedProduct)).thenReturn(productDto);

        ProductDto result = productService.updateProduct(1L, request);

        assertEquals(productDto, result);

        Mockito.verify(productRepository).findById(1L);
        Mockito.verify(productRepository).save(updatedProduct);
        Mockito.verify(productMapper).convertToProductDto(updatedProduct);
    }

    @Test
    void testUpdateUser_whenCalledIdInValid_itShouldThrowProductNotFoundException() {

        UpdateProductRequest request = generateUpdateProductRequest();

        Mockito.when(productRepository.findById(1L)).thenThrow(ProductNotFoundException.class);

        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(1L, request));

        Mockito.verify(productRepository).findById(1L);
        Mockito.verifyNoInteractions(productMapper);
    }
}
