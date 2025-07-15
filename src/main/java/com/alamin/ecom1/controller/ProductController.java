package com.alamin.ecom1.controller;
import com.alamin.ecom1.dto.ProductRequestDto;
import com.alamin.ecom1.dto.ProductResponseDto;
import com.alamin.ecom1.productEntity.Product;
import com.alamin.ecom1.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")

public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product product = service.getProductById(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @PostMapping("/product")
    public ResponseEntity<ProductResponseDto> addProduct(
            @RequestPart ProductRequestDto product,
            @RequestPart(required = false) MultipartFile imageFile) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.addProduct(product, imageFile));
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

   @PutMapping("/product/{id}")

    public ResponseEntity<?> updateProduct(@PathVariable int id,
                                           @RequestPart("product") ProductRequestDto requestDto,
                                           @RequestPart(required = false) MultipartFile imageFile) {

       ProductResponseDto product1;
       try {
           product1 = service.updateProduct(id, requestDto, imageFile);

       } catch (IOException e) {
           return new ResponseEntity<>("Failed to update image", HttpStatus.BAD_REQUEST);
       }

       return new ResponseEntity<>(product1, HttpStatus.OK);


   }
    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
        Product product = service.getProductById(productId);
        byte[] imageFile =product.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);

    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        service.deleteProduct(id);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<ProductResponseDto>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(service.searchProducts(keyword));
    }
}