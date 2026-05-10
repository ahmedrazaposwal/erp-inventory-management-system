package com.arp.erp_backend.service;

import com.arp.erp_backend.dto.supplier.SupplierRequestDTO;
import com.arp.erp_backend.dto.supplier.SupplierResponseDTO;

import java.util.List;

public interface SupplierService {

    SupplierResponseDTO createSupplier(SupplierRequestDTO requestDTO);

    List<SupplierResponseDTO> getAllSuppliers();

    SupplierResponseDTO getSupplierById(Long id);

    SupplierResponseDTO updateSupplier(Long id, SupplierRequestDTO requestDTO);

    void deleteSupplier(Long id);

    List<SupplierResponseDTO> searchSuppliers(String companyName);
}
