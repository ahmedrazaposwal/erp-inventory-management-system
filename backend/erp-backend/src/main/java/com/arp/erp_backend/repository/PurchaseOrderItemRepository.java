package com.arp.erp_backend.repository;

import com.arp.erp_backend.entity.PurchaseOrder;
import com.arp.erp_backend.entity.PurchaseOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItem, Long> {
    Optional<PurchaseOrderItem> findByPurchaseOrder(PurchaseOrder purchaseOrder);
}
