package com.isi.auth_network.category;


import com.isi.auth_network.exception.CategoryNotFoundException;
import com.isi.auth_network.exception.NameConflicException;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public Integer addCategory(CategoryRequest request){
        if(repository.findByName(request.name()).isPresent()){
            throw new NameConflicException("Le nom de category existe deja");
        }
        var category = repository.save(mapper.toCategory(request));
        return category.getId();
    }
    public List<CategoryResponse> allCategory() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCategory)
                .collect(Collectors.toList());
    }
    public CategoryResponse findById(Integer categoryId) {
        return repository.findById(categoryId)
                .map(mapper::fromCategory)
                .orElseThrow(() -> new CategoryNotFoundException(
                        String.format("La category non trouvée")
                ));
    }
    public void updateCategory(UpdateCategoryResponse request) {
        var category = repository.findById(request.id())
                .orElseThrow(() -> new CategoryNotFoundException(
                        String.format("La Categorie non trouvé ID:: %s", request.id())
                ));
        mergeCategory(category,request);
        repository.save(category);
    }

    private void mergeCategory(Category category, UpdateCategoryResponse request) {
        String name = request.name();
        String description = request.description();

        if (StringUtils.isNotBlank(name)) {
            if (!name.equals(category.getName()) && repository.findByName(name).isPresent()) {
                throw new NameConflicException("Cette catégorie existe déjà !");
            }
            category.setName(name);
        }

        if (StringUtils.isNotBlank(description)) {
            category.setDescription(description);
        }
    }


    public Boolean existById(Integer categoryId) {
        return repository.findById(categoryId)
                .isPresent();
    }
    public void deleteCategory(Integer categoryId) {
        if (!repository.existsById(categoryId)) {
            throw new CategoryNotFoundException(
                    String.format("La Categorie non trouvée ID:: %s", categoryId)
            );
        }
        repository.deleteById(categoryId);
    }
}
