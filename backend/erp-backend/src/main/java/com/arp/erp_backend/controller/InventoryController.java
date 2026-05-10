package com.arp.erp_backend.controller;

import com.arp.erp_backend.dto.inventory.InventoryRequestDTO;
import com.arp.erp_backend.dto.inventory.InventoryResponseDTO;
import com.arp.erp_backend.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping("/stock-in")
    public ResponseEntity<InventoryResponseDTO> stockIn(@Valid @RequestBody InventoryRequestDTO requestDTO) {
        return ResponseEntity.ok(inventoryService.stockIn(requestDTO));
    }

    @PostMapping("/stock-out")
    public ResponseEntity<InventoryResponseDTO> stockOut(@Valid @RequestBody InventoryRequestDTO requestDTO) {
        return ResponseEntity.ok(inventoryService.stockOut(requestDTO));
    }

    @GetMapping("/history/{productId}")
    public ResponseEntity<List<InventoryResponseDTO>> getHistory(@PathVariable Long productId) {
        return ResponseEntity.ok(inventoryService.getInventoryHistory(productId));
    }
}
