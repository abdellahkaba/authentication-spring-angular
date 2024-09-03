package com.isi.auth_network.category;


import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toCategory(CategoryRequest request) {
        if (request == null) {
            return null ;
        }
        return Category.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .build();
    }

    public CategoryResponse fromCategory(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}
