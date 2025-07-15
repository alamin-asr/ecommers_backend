package com.alamin.ecom1.service.impl;
import com.alamin.ecom1.dto.ProductRequestDto;
import com.alamin.ecom1.dto.ProductResponseDto;
import com.alamin.ecom1.mapper.ProductMapper;
import com.alamin.ecom1.productEntity.Product;
import com.alamin.ecom1.repo.ProductRepo;
import com.alamin.ecom1.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo repo;
    private final ProductMapper mapper;
    private  Product product;
    public ProductServiceImpl(ProductRepo repo, ProductMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<ProductResponseDto> getAllProducts() {
        return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public Product getProductById(int id) {
         product = repo.findById(id).orElse(null);
        return product != null ? product : null;
    }


    @Override
    @Transactional
    public ProductResponseDto addProduct(ProductRequestDto dto,
                                         MultipartFile imageFile) throws IOException {

        product = mapper.toEntity(dto);

        if (imageFile != null && !imageFile.isEmpty()) {
            product.setImageName(imageFile.getOriginalFilename());
            product.setImageType(imageFile.getContentType());
            product.setImageData(imageFile.getBytes());
        } else {
            product.setImageName(null);
            product.setImageType(null);
            product.setImageData(null);
        }
        Product saved = repo.save(product);

        return mapper.toDto(saved);
    }

    public ProductResponseDto updateProduct(int id, ProductRequestDto requestDto, MultipartFile imageFile) throws IOException {

        Product product = repo.findById(id).orElse(null);
       mapper.updateEntityFromDto(requestDto, product);

            product.setImageName(imageFile.getOriginalFilename());
            product.setImageType(imageFile.getContentType());
            product.setImageData(imageFile.getBytes());

        return mapper.toDto(repo.save(product));
    }


    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public List<ProductResponseDto> searchProducts(String keyword) {
        return repo.searchProducts(keyword).stream().map(mapper::toDto).collect(Collectors.toList());
    }
}