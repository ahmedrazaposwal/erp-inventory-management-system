package com.arp.erp_backend.dto.inventory;

import com.arp.erp_backend.entity.InventoryMovementType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class InventoryTransactionResponseDTO {

    private Long id;
    private Long productId;
    private String productName;
    private Integer quantity;
    private InventoryMovementType movementType;
    private String notes;
    private LocalDateTime createdAt;
}
