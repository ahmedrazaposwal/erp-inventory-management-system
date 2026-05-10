package com.arp.erp_backend.service;

import com.arp.erp_backend.dto.product.ProductRequestDTO;
import com.arp.erp_backend.dto.product.ProductResponseDTO;
import com.arp.erp_backend.entity.Product;
import com.arp.erp_backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDTO createProduct(ProductRequestDTO request) {
        if (productRepository.findBySku(request.getSku()).isPresent()) {
            throw new RuntimeException("SKU already exists");
        }

        Product product = Product.builder()
                .sku(request.getSku())
                .name(request.getName())
                .description(request.getDescription())
                .quantity(request.getQuantity())
                .costPrice(request.getCostPrice())
                .sellingPrice(request.getSellingPrice())
                .category(request.getCategory())
                .active(true)
                .build();

        Product savedProduct = productRepository.save(product);
        return mapToResponse(savedProduct);
    }

    public Page<ProductResponseDTO> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        return productRepository.findByActiveTrue(pageable).map(this::mapToResponse);
    }

    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return mapToResponse(product);
    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setQuantity(request.getQuantity());
        product.setCostPrice(request.getCostPrice());
        product.setSellingPrice(request.getSellingPrice());
        product.setCategory(request.getCategory());

        Product updatedProduct = productRepository.save(product);
        return mapToResponse(updatedProduct);
    }

    public void softDeleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setActive(false);
        productRepository.save(product);
    }

    public Page<ProductResponseDTO> searchProductsByName(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return productRepository.findByNameContainingIgnoreCase(keyword, pageable)
                .map(this::mapToResponse);
    }

    public Page<ProductResponseDTO> searchProductsByCategory(String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return productRepository.findByCategoryContainingIgnoreCase(category, pageable)
                .map(this::mapToResponse);
    }

    private ProductResponseDTO mapToResponse(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .sellingPrice(product.getSellingPrice())
                .category(product.getCategory())
                .build();
    }
}
