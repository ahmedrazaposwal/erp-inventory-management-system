package com.arp.erp_backend.service;

import com.arp.erp_backend.dto.inventory.InventoryTransactionRequestDTO;
import com.arp.erp_backend.dto.inventory.InventoryTransactionResponseDTO;

import java.util.List;

public interface InventoryTransactionService {
    InventoryTransactionResponseDTO stockIn(InventoryTransactionRequestDTO requestDTO);
    InventoryTransactionResponseDTO stockOut(InventoryTransactionRequestDTO requestDTO);
    List<InventoryTransactionResponseDTO> getInventoryHistory(Long productId);
}
