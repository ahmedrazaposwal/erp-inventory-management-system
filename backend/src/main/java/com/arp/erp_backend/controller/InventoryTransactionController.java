package com.arp.erp_backend.controller;

import com.arp.erp_backend.dto.inventory.InventoryTransactionRequestDTO;
import com.arp.erp_backend.dto.inventory.InventoryTransactionResponseDTO;
import com.arp.erp_backend.service.InventoryTransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryTransactionController {
    private final InventoryTransactionService inventoryService;

    @PostMapping("/stock-in")
    public ResponseEntity<InventoryTransactionResponseDTO> stockIn(@Valid @RequestBody InventoryTransactionRequestDTO requestDTO) {
        return ResponseEntity.ok(inventoryService.stockIn(requestDTO));
    }

    @PostMapping("/stock-out")
    public ResponseEntity<InventoryTransactionResponseDTO> stockOut(@Valid @RequestBody InventoryTransactionRequestDTO requestDTO) {
        return ResponseEntity.ok(inventoryService.stockOut(requestDTO));
    }

    @GetMapping("/history/{productId}")
    public ResponseEntity<List<InventoryTransactionResponseDTO>> getHistory(@PathVariable Long productId) {
        return ResponseEntity.ok(inventoryService.getInventoryHistory(productId));
    }
}
