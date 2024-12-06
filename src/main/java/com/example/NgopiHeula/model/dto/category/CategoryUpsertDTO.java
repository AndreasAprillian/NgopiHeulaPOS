package com.example.NgopiHeula.model.dto.category;

import com.example.NgopiHeula.validation.UniqueCategoryName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Schema(description = "Data form Category yang digunakan untuk insert dan update.")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@UniqueCategoryName(message="Category with this name is already exist.", idField = "categoryId", nameField ="name")
public class CategoryUpsertDTO {
    @Schema(description = "PK Category.")
    private Integer categoryId;

    @Schema(description = "Nama category maximum 50 characters.")
    @NotBlank(message="Category name is required")
    @Size(max=50, message="Category name can't be more than 50 characters.")
    private String name;

    @Schema(description = "Description maximum 255 characters.")
    @Size(max=255, message="Category description can't be more than 255 characters.")
    private String description;
}
