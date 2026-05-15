package com.arp.erp_backend.service;

import com.arp.erp_backend.dto.inventory.InventoryTransactionRequestDTO;
import com.arp.erp_backend.dto.inventory.InventoryTransactionResponseDTO;
import com.arp.erp_backend.entity.InventoryMovementType;
import com.arp.erp_backend.entity.InventoryTransaction;
import com.arp.erp_backend.entity.Product;
import com.arp.erp_backend.repository.InventoryTransactionRepository;
import com.arp.erp_backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryTransactionServiceImpl implements InventoryTransactionService {

    private final InventoryTransactionRepository inventoryRepository;
    private  final ProductRepository productRepository;

    @Override
    public InventoryTransactionResponseDTO stockIn(InventoryTransactionRequestDTO requestDTO) {
        Product product = productRepository.findById(requestDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setQuantity(product.getQuantity() + requestDTO.getQuantity());
        productRepository.save(product);

        InventoryTransaction transaction = InventoryTransaction.builder()
                .product(product)
                .quantity(requestDTO.getQuantity())
                .movementType(InventoryMovementType.STOCK_IN)
                .notes(requestDTO.getNotes())
                .referenceNumber("IN-" + System.currentTimeMillis())
                .build();

        InventoryTransaction savedTransaction =  inventoryRepository.save(transaction);
        return mapToResponse(savedTransaction);
    }

    @Override
    public InventoryTransactionResponseDTO stockOut(InventoryTransactionRequestDTO requestDTO) {
        Product product = productRepository.findById(requestDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getQuantity() < requestDTO.getQuantity()) {
            throw new RuntimeException("Insufficient stock available");
        }
        product.setQuantity(product.getQuantity() - requestDTO.getQuantity());
        productRepository.save(product);

        InventoryTransaction transaction = InventoryTransaction.builder()
                .product(product)
                .quantity(requestDTO.getQuantity())
                .movementType(InventoryMovementType.STOCK_OUT)
                .notes(requestDTO.getNotes())
                .referenceNumber("OUT-" + System.currentTimeMillis())
                .build();

        InventoryTransaction savedTransaction = inventoryRepository.save(transaction);

        return mapToResponse(savedTransaction);
    }

    @Override
    public List<InventoryTransactionResponseDTO> getInventoryHistory(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return inventoryRepository.findByProduct(product)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private InventoryTransactionResponseDTO mapToResponse(InventoryTransaction transaction) {
        return InventoryTransactionResponseDTO.builder()
                .id(transaction.getId())
                .productId(transaction.getProduct().getId())
                .productName(transaction.getProduct().getName())
                .quantity(transaction.getQuantity())
                .movementType(transaction.getMovementType())
                .notes(transaction.getNotes())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}
