package com.arp.erp_backend.controller;

import com.arp.erp_backend.dto.purchaseorder.PurchaseOrderRequestDTO;
import com.arp.erp_backend.dto.purchaseorder.PurchaseOrderResponseDTO;
import com.arp.erp_backend.service.PurchaseOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
@RequiredArgsConstructor
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @PostMapping
    public ResponseEntity<PurchaseOrderResponseDTO> createPurchaseOrder(
            @Valid
            @RequestBody PurchaseOrderRequestDTO requestDTO) {
        PurchaseOrderResponseDTO responseDTO = purchaseOrderService.createPurchaseOrder(requestDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrderResponseDTO>> getAllPurchaseOrders() {
        return ResponseEntity.ok(purchaseOrderService.getAllPurchaseOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderResponseDTO> getPurchaseOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseOrderService.getPurchaseOrderById(id));
    }

    @PutMapping("/{id}/receive")
    public ResponseEntity<PurchaseOrderResponseDTO>  receivePurchaseOrder(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseOrderService.receivePurchaseOrder(id));
    }


}
