package com.example.NgopiHeula.model.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Data Category yang untuk ditampilkan di table.")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryAllDTO {
    @Schema(description = "PK Category.")
    private Integer categoryId;

    @Schema(description = "Nama category product.")
    private String name;

    @Schema(description = "Deskripsi dari category.")
    private String description;

}
