package com.arp.erp_backend.dto.purchaseorder;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PurchaseOrderRequestDTO {

    @NotNull(message = "Supplier ID is required")
    private Long supplierId;

    @Valid
    @NotEmpty(message = "Purchase order must contain items")
    private List<PurchaseOrderItemRequestDTO> items;
}
