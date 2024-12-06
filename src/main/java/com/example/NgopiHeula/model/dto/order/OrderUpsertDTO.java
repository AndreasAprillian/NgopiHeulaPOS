package com.example.NgopiHeula.model.dto.order;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "Data yang digunakan untuk menambahkan dan mengubah order baru.")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderUpsertDTO {
    @Schema(description = "PK Nomor invoice, maximum 20 characters.")
    private Integer invoiceNumber;

    @Schema(description = "Nama Kasir yang melayani, maximum 20 characters.")
    @Size(max=20, message="Destination city can't be more than 20 characters.")
    private String username;

    @Schema(description = "Nama lengkap customer, maximum 20 characters.")
    @NotBlank(message="Client Name is required.")
    @Size(max=20, message="client name can't be more than 20 characters.")
    private String clientName;

    @Schema(description = "Tanggal pemesanan dalam format yyyy-MM-dd.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime orderDate;
}
