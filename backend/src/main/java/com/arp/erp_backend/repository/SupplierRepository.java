package com.arp.erp_backend.repository;

import com.arp.erp_backend.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findBySupplierCode(String supplierCode);
    List<Supplier> findByActiveTrue();
    List<Supplier> findByCompanyNameContainingIgnoreCase(String companyName);
}
