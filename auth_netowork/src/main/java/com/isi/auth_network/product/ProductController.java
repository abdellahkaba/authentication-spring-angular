package com.isi.auth_network.product;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
@Tag(name = "Product")
public class ProductController {

    private final ProductService service ;
    @PostMapping
    public ResponseEntity<Integer> addProduct(
            @RequestBody @Valid ProductRequest request
    ){
        return ResponseEntity.ok(service.addProduct(request));
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> allProduct(){
        return ResponseEntity.ok(service.allProduct());
    }
    @GetMapping("{Product-id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("Product-id") Integer ProductId
    ){
        return ResponseEntity.ok(service.findById(ProductId));
    }
    @PutMapping("/{product-id}")
    public ResponseEntity<Void> updateProduct(
            @PathVariable("product-id") Integer productId,
            @RequestBody @Valid UpdateProductResponse request
    ){
        request = new UpdateProductResponse(
                productId,
                request.name(),
                request.price(),
                request.quantity(),
                request.category(),
                request.description());
        service.updateProduct(request);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/exists/{product-id}")
    public ResponseEntity<Boolean> existById(
            @PathVariable("product-id") Integer productId
    ){
        return ResponseEntity.ok(service.existById(productId));
    }
    @DeleteMapping("/{product-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("product-id") Integer productId
    ){
        service.deleteProduct(productId);
        return ResponseEntity.accepted().build();
    }
}
