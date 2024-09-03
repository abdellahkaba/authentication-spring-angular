package com.isi.auth_network.product;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toProduct(ProductRequest request) {
        if (request == null){
            return null;
        }
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .price(request.price())
                .quantity(request.quantity())
                .category(request.category())
                .description(request.description())
                .build();
    }

    public ProductResponse fromProduct(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory().getName(),
                product.getDescription()
        );
    }
}
