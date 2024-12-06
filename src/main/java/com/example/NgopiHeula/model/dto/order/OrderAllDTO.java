package com.example.NgopiHeula.model.dto.order;

import com.example.NgopiHeula.utility.FormatHelper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Schema(description = "Data transaksi order yang akan ditampilkan di halaman index grid.")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderAllDTO {
    @Schema(description = "Nomor invoice PK.")
    private Integer invoiceNumber;

    @Schema(description = "Nama Kasir yang melayani.")
    private String username;

    @Schema(description = "Nama lengkap customer.")
    private String clientName;

    @Schema(description = "Tanggal pemesanan order.")
    private LocalDateTime orderDate;

    private String formatedOrderDate;

    public OrderAllDTO(Integer invoiceNumber,String username, String clientName, LocalDateTime orderDate){
        this.invoiceNumber = invoiceNumber;
        this.username = username;
        this.clientName = clientName;
        this.formatedOrderDate = FormatHelper.FormatTanggal(orderDate,"dd-mm-yyyy HH:mm:ss");
    }
}
