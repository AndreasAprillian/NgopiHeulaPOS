package com.example.NgopiHeula.model.entity;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "Order_details")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id", nullable = false)
    private Integer orderDetailId;

    @Column(name = "invoice_number", nullable = false)
    private Integer invoiceNumber;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "invoice_number", nullable = false, insertable=false, updatable=false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, insertable=false, updatable=false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

}
