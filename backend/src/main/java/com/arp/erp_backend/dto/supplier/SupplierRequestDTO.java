package com.arp.erp_backend.dto.supplier;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SupplierRequestDTO {

    @NotBlank(message = "Supplier code is required")
    private String supplierCode;

    @NotBlank(message = "Company name is required")
    private String companyName;

    private String contactPerson;

    @Email(message = "Invalid email format")
    private String email;

    private String phone;

    private String address;

    private String country;

    private String taxNumber;
}
