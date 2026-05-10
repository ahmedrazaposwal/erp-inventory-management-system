package com.arp.erp_backend.dto.product;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDTO {

    @NotBlank
    private String sku;

    @NotBlank
    private String name;
    private String description;

    @NotNull
    @Min(0)
    private Integer quantity;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal costPrice;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal sellingPrice;

    private String category;
}
