package com.arp.erp_backend.dto.purchaseorder;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PurchaseOrderItemResponseDTO {
    private Long productId;

    private String productName;

    private Integer quantity;

    private BigDecimal costPrice;

    private BigDecimal lineTotal;
}
