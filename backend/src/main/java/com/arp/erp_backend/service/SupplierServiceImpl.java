package com.arp.erp_backend.service;

import com.arp.erp_backend.dto.supplier.SupplierRequestDTO;
import com.arp.erp_backend.dto.supplier.SupplierResponseDTO;
import com.arp.erp_backend.entity.Supplier;
import com.arp.erp_backend.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl  implements SupplierService{

    private final SupplierRepository supplierRepository;

    @Override
    public SupplierResponseDTO createSupplier(SupplierRequestDTO requestDTO) {
        Supplier supplier = Supplier.builder()
                .supplierCode(requestDTO.getSupplierCode())
                .companyName(requestDTO.getCompanyName())
                .contactPerson(requestDTO.getContactPerson())
                .email(requestDTO.getEmail())
                .phone(requestDTO.getPhone())
                .address(requestDTO.getAddress())
                .country(requestDTO.getCountry())
                .taxNumber(requestDTO.getTaxNumber())
                .build();

        Supplier savedSupplier = supplierRepository.save(supplier);
        return mapToResponse(savedSupplier);
    }

    @Override
    public List<SupplierResponseDTO> getAllSuppliers() {
        return supplierRepository.findByActiveTrue()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierResponseDTO getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        return mapToResponse(supplier);
    }

    @Override
    public SupplierResponseDTO updateSupplier(Long id, SupplierRequestDTO requestDTO) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        supplier.setSupplierCode(requestDTO.getSupplierCode());
        supplier.setCompanyName(requestDTO.getCompanyName());
        supplier.setContactPerson(requestDTO.getContactPerson());
        supplier.setEmail(requestDTO.getEmail());
        supplier.setPhone(requestDTO.getPhone());
        supplier.setAddress(requestDTO.getAddress());
        supplier.setCountry(requestDTO.getCountry());
        supplier.setTaxNumber(requestDTO.getTaxNumber());

        Supplier updatedSupplier = supplierRepository.save(supplier);
        return mapToResponse(updatedSupplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        supplier.setActive(false);
        supplierRepository.save(supplier);
    }

    @Override
    public List<SupplierResponseDTO> searchSuppliers(String companyName) {
        return supplierRepository.findByCompanyNameContainingIgnoreCase(companyName)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private SupplierResponseDTO mapToResponse(Supplier savedSupplier) {
        return SupplierResponseDTO.builder()
                .id(savedSupplier.getId())
                .supplierCode(savedSupplier.getSupplierCode())
                .companyName(savedSupplier.getCompanyName())
                .contactPerson(savedSupplier.getContactPerson())
                .email(savedSupplier.getEmail())
                .phone(savedSupplier.getPhone())
                .address(savedSupplier.getAddress())
                .country(savedSupplier.getCountry())
                .taxNumber(savedSupplier.getTaxNumber())
                .active(savedSupplier.getActive())
                .createdAt(savedSupplier.getCreatedAt())
                .build();
    }
}
