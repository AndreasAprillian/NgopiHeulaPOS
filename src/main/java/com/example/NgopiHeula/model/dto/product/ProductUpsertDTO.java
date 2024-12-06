package com.example.NgopiHeula.model.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "Data product di dalam form yang digunakan untuk insert dan update.")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductUpsertDTO {
    @Schema(description = "Product PK.")
    private Integer productId;

    @Schema(description = "Category ID FK")
    @NotNull(message="Category is required.")
    private Integer categoryId;

    @Schema(description = "Nama product, maximum 50 characters.")
    @NotBlank(message="Product name is required.")
    @Size(max=200, message="Product name can't be no more than 50 characters.")
    private String name;

    @Schema(description = "Harga product ditulis dalam angka desimal 2.")
    @NotNull(message="Unit price is required.")
    @Digits(integer=10, fraction=2, message="Must be a decimal value with 2 decimal points.")
    private BigDecimal price;

    @Schema(description = "Apakah product ini masih tersedia.")
    @NotNull(message="Discontinue is required.")
    private Boolean isAvailable;
}
