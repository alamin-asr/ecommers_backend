package com.alamin.ecom1.mapper;


import com.alamin.ecom1.dto.ProductRequestDto;
import com.alamin.ecom1.dto.ProductResponseDto;
import com.alamin.ecom1.productEntity.Product;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "brand", target = "brand"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "category", target = "category"),
            @Mapping(source = "releaseDate", target = "releaseDate"),
            @Mapping(source = "productAvailable", target = "productAvailable"),
            @Mapping(source = "stockQuantity", target = "stockQuantity"),
            @Mapping(target = "imageName", ignore = true),
            @Mapping(target = "imageType", ignore = true)
    })
    ProductResponseDto toDto(Product product);

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "brand", target = "brand"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "category", target = "category"),
            @Mapping(source = "releaseDate", target = "releaseDate"),
            @Mapping(source = "productAvailable", target = "productAvailable"),
            @Mapping(source = "stockQuantity", target = "stockQuantity"),
            @Mapping(target = "imageName", ignore = true),
            @Mapping(target = "imageType", ignore = true),
            @Mapping(target = "imageData", ignore = true)
    })
    Product toEntity(ProductRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ProductRequestDto dto, @MappingTarget Product entity);

}