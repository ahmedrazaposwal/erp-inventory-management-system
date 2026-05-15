package com.arp.erp_backend.dto.purchaseorder;

import com.arp.erp_backend.entity.PurchaseOrderStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PurchaseOrderResponseDTO {

    private Long id;

    private String poNumber;

    private String supplierName;

    private PurchaseOrderStatus status;

    private BigDecimal totalAmount;

    private LocalDateTime orderDate;

    private List<PurchaseOrderItemResponseDTO> items;
}
