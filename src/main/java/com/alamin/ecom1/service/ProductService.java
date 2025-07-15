package com.alamin.ecom1.service;

import com.alamin.ecom1.dto.ProductRequestDto;
import com.alamin.ecom1.dto.ProductResponseDto;
import com.alamin.ecom1.productEntity.Product;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<ProductResponseDto> getAllProducts();

    Product getProductById(int id);

    ProductResponseDto addProduct(ProductRequestDto requestDto, MultipartFile imageFile) throws IOException;

    ProductResponseDto updateProduct(int id, ProductRequestDto requestDto, MultipartFile imageFile) throws IOException;

    void deleteProduct(int id);

    List<ProductResponseDto> searchProducts(String keyword);


}