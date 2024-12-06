package com.example.NgopiHeula.model.dto.product;

import com.example.NgopiHeula.utility.FormatHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Schema(description = "Data yang akan ditunjukan di product grid di halaman index.")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class ProductAllDTO {
    @Schema(description = "Product PK.")
    private Integer productId;

    @Schema(description = "Nama product, nama product tidak bersifat unik.")
    private String name;

    @Schema(description = "Nama dari category product")
    private String categoryName;

    @JsonIgnore
    private BigDecimal price;

    @JsonIgnore
    private Boolean isAvailable;

    @Schema(description = "Ketersediaan product")
    private String isAvailableFormated;

    @Schema(description = "Harga product dalam rupiah.")
    private String formatedPrice;

    public ProductAllDTO(Integer productId,String name,String categoryName,BigDecimal price, Boolean isAvailable){
        this.productId = productId;
        this.name = name;
        this.categoryName = categoryName;
        this.price = price;
        this.isAvailable = isAvailable;
        this.formatedPrice = FormatHelper.FormatCurrency(price);
        this.isAvailableFormated = isAvailable ? "Available" : "Not Available";
    }

}
