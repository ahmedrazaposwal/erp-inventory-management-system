package com.arp.erp_backend.service;

import com.arp.erp_backend.dto.purchaseorder.PurchaseOrderRequestDTO;
import com.arp.erp_backend.dto.purchaseorder.PurchaseOrderResponseDTO;

import java.util.List;

public interface PurchaseOrderService {
    PurchaseOrderResponseDTO createPurchaseOrder(PurchaseOrderRequestDTO requestDTO);
    List<PurchaseOrderResponseDTO> getAllPurchaseOrders();
    PurchaseOrderResponseDTO getPurchaseOrderById(Long id);
    PurchaseOrderResponseDTO receivePurchaseOrder(Long id);
}
