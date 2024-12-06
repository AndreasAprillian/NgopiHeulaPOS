package com.example.NgopiHeula.model.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Schema(description = "Data Account untuk read only.")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountViewDTO {
    @Schema(description = "Username maximum 20 characters.")
    @NotBlank(message="Username is required")
    @Size(max=20, message="Username can't be more than 20 characters.")
    private String username;

    @Schema(description = "Pilih role antara Administrator, Salesman, atau Finance.")
    @NotBlank(message="Role is required")
    private String role;
}
