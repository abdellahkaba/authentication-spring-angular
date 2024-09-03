package com.isi.auth_network.product;

import com.isi.auth_network.category.Category;

public record ProductResponse(
        Integer id,
        String name,
        double price,
        Integer quantity,
        String categoryName,
        String description
) {
}
