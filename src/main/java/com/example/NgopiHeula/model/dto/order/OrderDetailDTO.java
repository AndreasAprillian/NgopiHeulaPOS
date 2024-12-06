package com.example.NgopiHeula.model.dto.order;

import com.example.NgopiHeula.utility.FormatHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailDTO {
    private Integer orderDetailId;

    private Integer invoiceNumber;

    private Integer productId;

    private String productName; // Nama produk (diambil dari relasi Product)

    private Integer quantity;

    private BigDecimal price;

    private String  formatedPrice;

    private String  totalPrice; // Harga total (quantity * price)

    public OrderDetailDTO(Integer orderDetailId, Integer invoiceNumber, Integer productId,
                          String productName, Integer quantity, BigDecimal price) {
        this.orderDetailId = orderDetailId;
        this.invoiceNumber = invoiceNumber;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.formatedPrice = FormatHelper.FormatCurrency(price);
        this.totalPrice = FormatHelper.FormatCurrency(new BigDecimal(quantity).multiply(price));
    }
}
