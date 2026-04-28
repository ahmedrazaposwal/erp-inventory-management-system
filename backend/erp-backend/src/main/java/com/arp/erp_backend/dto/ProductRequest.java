package com.arp.erp_backend.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    @NotBlank
    private String sku;

    @NotBlank
    private String name;
    private String description;

    @NotBlank
    @Min(0)
    private Integer quantity;

    @NotBlank
    @DecimalMin("0.0")
    private BigDecimal costPrice;

    @NotBlank
    @DecimalMin("0.0")
    private BigDecimal sellingPrice;

    private String category;
}
