package com.arp.erp_backend.controller;

import com.arp.erp_backend.dto.supplier.SupplierRequestDTO;
import com.arp.erp_backend.dto.supplier.SupplierResponseDTO;
import com.arp.erp_backend.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<SupplierResponseDTO> createSupplier(@Valid @RequestBody SupplierRequestDTO requestDTO) {
        return ResponseEntity.ok(supplierService.createSupplier(requestDTO));
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponseDTO>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> getSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> updateSupplier(@PathVariable Long id,
                                                              @Valid @RequestBody SupplierRequestDTO requestDTO) {
        return ResponseEntity.ok(supplierService.updateSupplier(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok("Supplier deactivated successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<List<SupplierResponseDTO>> searchSuppliers(@RequestParam String companyName) {
        return ResponseEntity.ok(supplierService.searchSuppliers(companyName));
    }

}
