package com.arp.erp_backend.service;

import com.arp.erp_backend.dto.purchaseorder.PurchaseOrderItemRequestDTO;
import com.arp.erp_backend.dto.purchaseorder.PurchaseOrderItemResponseDTO;
import com.arp.erp_backend.dto.purchaseorder.PurchaseOrderRequestDTO;
import com.arp.erp_backend.dto.purchaseorder.PurchaseOrderResponseDTO;
import com.arp.erp_backend.entity.*;
import com.arp.erp_backend.repository.InventoryTransactionRepository;
import com.arp.erp_backend.repository.ProductRepository;
import com.arp.erp_backend.repository.PurchaseOrderRepository;
import com.arp.erp_backend.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final InventoryTransactionRepository inventoryTransactionRepository;


    @Override
    @Transactional
    public PurchaseOrderResponseDTO createPurchaseOrder(PurchaseOrderRequestDTO requestDTO) {
        Supplier supplier = supplierRepository.findById(requestDTO.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .poNumber(generatePoNumber())
                .supplier(supplier)
                .status(PurchaseOrderStatus.PENDING)
                .build();

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (PurchaseOrderItemRequestDTO itemRequestDTO : requestDTO.getItems()) {
            Product product = productRepository.findById(itemRequestDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            BigDecimal lineTotal = itemRequestDTO.getCostPrice()
                    .multiply(BigDecimal.valueOf(itemRequestDTO.getQuantity()));

            PurchaseOrderItem item = PurchaseOrderItem.builder()
                    .purchaseOrder(purchaseOrder)
                    .product(product)
                    .quantity(itemRequestDTO.getQuantity())
                    .costPrice(itemRequestDTO.getCostPrice())
                    .lineTotal(lineTotal)
                    .build();

            purchaseOrder.getItems().add(item);
            totalAmount = totalAmount.add(lineTotal);
        }

        purchaseOrder.setTotalAmount(totalAmount);

        PurchaseOrder savedPurchaseOrder = purchaseOrderRepository.save(purchaseOrder);

        return mapToResponse(savedPurchaseOrder);
    }

    @Override
    public List<PurchaseOrderResponseDTO> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseOrderResponseDTO getPurchaseOrderById(Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order not found"));

        return mapToResponse(purchaseOrder);
    }

    @Override
    public PurchaseOrderResponseDTO receivePurchaseOrder(Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order not found"));

        if (purchaseOrder.getStatus() == PurchaseOrderStatus.RECEIVED) {
            throw  new RuntimeException("Purchase Order already received");
        }

        for (PurchaseOrderItem item : purchaseOrder.getItems()) {
            Product product = item.getProduct();

            Integer currentQuantity = product.getQuantity();
            Integer newQuantity = currentQuantity + item.getQuantity();

            product.setQuantity(newQuantity);

            InventoryTransaction transaction = InventoryTransaction.builder()
                    .product(product)
                    .quantity(item.getQuantity())
                    .movementType(InventoryMovementType.STOCK_IN)
                    .referenceNumber(purchaseOrder.getPoNumber())
                    .notes("Stock received from purchase order")
                    .build();

            inventoryTransactionRepository.save(transaction);
        }

        purchaseOrder.setStatus(PurchaseOrderStatus.RECEIVED);

        PurchaseOrder savedPurchaseOrder = purchaseOrderRepository.save(purchaseOrder);

        return mapToResponse(savedPurchaseOrder);
    }

    private String generatePoNumber() {
        return "PO-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private PurchaseOrderResponseDTO mapToResponse(PurchaseOrder purchaseOrder) {

        List<PurchaseOrderItemResponseDTO> itemResponseDTOS =
                purchaseOrder.getItems()
                        .stream()
                        .map(item ->
                                PurchaseOrderItemResponseDTO.builder()
                                        .productId(item.getProduct().getId())
                                        .productName(item.getProduct().getName())
                                        .quantity(item.getQuantity())
                                        .costPrice(item.getCostPrice())
                                        .lineTotal(item.getLineTotal())
                                        .build()
                        ).collect(Collectors.toList());

        return PurchaseOrderResponseDTO.builder()
                .id(purchaseOrder.getId())
                .poNumber(purchaseOrder.getPoNumber())
                .supplierName(purchaseOrder.getSupplier().getCompanyName())
                .status(purchaseOrder.getStatus())
                .totalAmount(purchaseOrder.getTotalAmount())
                .orderDate(purchaseOrder.getOrderDate())
                .items(itemResponseDTOS)
                .build();
    }
}
