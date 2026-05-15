package com.arp.erp_backend.repository;

import com.arp.erp_backend.entity.InventoryTransaction;
import com.arp.erp_backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction, Long> {
    List<InventoryTransaction> findByProduct(Product product);
}
