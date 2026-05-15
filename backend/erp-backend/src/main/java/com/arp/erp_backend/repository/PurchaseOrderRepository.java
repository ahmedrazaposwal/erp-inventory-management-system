package com.arp.erp_backend.repository;

import com.arp.erp_backend.entity.PurchaseOrder;
import com.arp.erp_backend.entity.PurchaseOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseOrderRepository  extends JpaRepository<PurchaseOrder, Long> {
    Optional<PurchaseOrder> findByPoNumber(String poNumber);
    List<PurchaseOrder> findByStatus(PurchaseOrderStatus status);
}
