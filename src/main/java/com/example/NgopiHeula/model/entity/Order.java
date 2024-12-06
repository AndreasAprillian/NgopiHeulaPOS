package com.example.NgopiHeula.model.entity;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_number", nullable = false)
    private Integer invoiceNumber;

    @Column(name = "username", nullable = false, updatable = false)
    private String username;

    @Column(name = "client_name", nullable = false, length = 50)
    private String clientName;

    @Column(name = "order_date", nullable = false, updatable = false)
    private LocalDateTime orderDate;

    @JsonIgnore  //untuk menghapus json object kosong ({})
    @OneToMany(mappedBy="order", //merupakan relation antar table, mappedBy menjelaskan relasi dia dengan table orderdetail dengan property order
            cascade={CascadeType.REMOVE} ) //setiap order dilakukan remove maka dia akan menghapus juga table order detail (table fk)
    private List<OrderDetail> orderDetails;

    public Order(Integer invoiceNumber, String username, String clientName, LocalDateTime orderDate) {
        this.invoiceNumber = invoiceNumber;
        this.username = username;
        this.clientName = clientName;
        this.orderDate = orderDate;
    }
}
