package com.isi.auth_network.category;

import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
        Integer id,
        @NotNull(message = "Le nom de category est requis")
        String name,
        String description
) {
}
