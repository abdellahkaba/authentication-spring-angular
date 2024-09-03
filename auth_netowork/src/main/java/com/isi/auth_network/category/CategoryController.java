package com.isi.auth_network.category;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("categories")
@Tag(name = "Category")
public class CategoryController {

    private final CategoryService service ;
    @PostMapping
    public ResponseEntity<Integer> addCategory(
            @RequestBody @Valid CategoryRequest request
    ){
        return ResponseEntity.ok(service.addCategory(request));
    }
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> allCategory(){
        return ResponseEntity.ok(service.allCategory());
    }
    @GetMapping("{category-id}")
    public ResponseEntity<CategoryResponse> findById(
            @PathVariable("category-id") Integer categoryId
    ){
        return ResponseEntity.ok(service.findById(categoryId));
    }
    @PutMapping("/{category-id}")
    public ResponseEntity<Void> updateCategory(
            @PathVariable("category-id") Integer categoryId,
            @RequestBody @Valid UpdateCategoryResponse request
    ){
        request = new UpdateCategoryResponse(
                categoryId,
                request.name(),
                request.description());
        service.updateCategory(request);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/exists/{category-id}")
    public ResponseEntity<Boolean> existById(
            @PathVariable("category-id") Integer categoryId
    ){
        return ResponseEntity.ok(service.existById(categoryId));
    }
    @DeleteMapping("/{category-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("category-id") Integer categoryId
    ){
        service.deleteCategory(categoryId);
        return ResponseEntity.accepted().build();
    }
}
