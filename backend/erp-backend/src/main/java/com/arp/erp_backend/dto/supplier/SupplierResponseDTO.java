package com.arp.erp_backend.dto.supplier;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SupplierResponseDTO {

    private Long id;

    private String supplierCode;

    private String companyName;

    private String contactPerson;

    private String email;

    private String phone;

    private String address;

    private String country;

    private String taxNumber;

    private Boolean active;

    private LocalDateTime createdAt;
}
