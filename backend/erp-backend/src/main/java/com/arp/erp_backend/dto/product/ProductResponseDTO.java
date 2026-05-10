package com.arp.erp_backend.dto.product;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductResponseDTO {

    private Long id;

    private String sku;

    private String name;

    private String description;

    private Integer quantity;

    private BigDecimal sellingPrice;

    private String category;
}
