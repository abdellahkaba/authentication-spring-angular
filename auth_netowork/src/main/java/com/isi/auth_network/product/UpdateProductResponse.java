package com.isi.auth_network.product;

import com.isi.auth_network.category.Category;

public record UpdateProductResponse(
        Integer id,
        String name,
        double price,
        Integer quantity,
        Category category,
        String description
) {
}
