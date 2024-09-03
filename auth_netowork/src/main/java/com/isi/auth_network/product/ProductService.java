package com.isi.auth_network.product;

import com.isi.auth_network.exception.ProductNotFoundException;
import com.isi.auth_network.exception.NameConflicException;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper ;
    public Integer addProduct(ProductRequest request) {
        if (repository.findByName(request.name()).isPresent()){
            throw new NameConflicException("Le nom de produit existe deja !") ;
        }
        var product = repository.save(mapper.toProduct(request));
        return product.getId();
    }

    public List<ProductResponse> allProduct() {
        return repository.findAll()
                .stream()
                .map(mapper::fromProduct)
                .collect(Collectors.toList());
    }
    public ProductResponse findById(Integer productId) {
        return repository.findById(productId)
                .map(mapper::fromProduct)
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("Le Produit non trouvée")
                ));
    }
    public void updateProduct(UpdateProductResponse request) {
        var product = repository.findById(request.id())
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("Le Produit non trouvé ID:: %s", request.id())
                ));
        mergeProduct(product,request);
        repository.save(product);
    }

    private void mergeProduct(Product product, UpdateProductResponse request) {
        if (StringUtils.isNotBlank(request.name()) &&
                !request.name().equals(product.getName()) &&
                repository.findByName(request.name()).isPresent()){
            throw new NameConflicException("Ce product existe deja !");
        }
        if(StringUtils.isNotBlank(request.name())){
            product.setName(request.name());
        }
        if (StringUtils.isNotBlank(request.description())){
            product.setDescription(request.description());
        }
    }

    public Boolean existById(Integer productId) {
        return repository.findById(productId)
                .isPresent();
    }
    public void deleteProduct(Integer productId) {
        if (!repository.existsById(productId)) {
            throw new ProductNotFoundException(
                    String.format("Le produit non trouvée ID:: %s", productId)
            );
        }
        repository.deleteById(productId);
    }
}
