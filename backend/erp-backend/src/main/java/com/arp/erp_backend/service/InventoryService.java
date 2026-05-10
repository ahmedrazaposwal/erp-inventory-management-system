package com.arp.erp_backend.service;

import com.arp.erp_backend.dto.inventory.InventoryRequestDTO;
import com.arp.erp_backend.dto.inventory.InventoryResponseDTO;

import java.util.List;

public interface InventoryService {
    InventoryResponseDTO stockIn(InventoryRequestDTO requestDTO);
    InventoryResponseDTO stockOut(InventoryRequestDTO requestDTO);
    List<InventoryResponseDTO> getInventoryHistory(Long productId);
}
