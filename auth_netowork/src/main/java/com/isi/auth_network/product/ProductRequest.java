package com.isi.auth_network.product;

import com.isi.auth_network.category.Category;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(
        Integer id,
        @NotNull(message = "Donnez le nom du produit")
        String name,
        @NotNull(message = "Fixer le prix du produit")
        double price,
        @NotNull(message = "Quelle quantité ?")
        Integer quantity,
        Category category ,
        String description
) {
}
