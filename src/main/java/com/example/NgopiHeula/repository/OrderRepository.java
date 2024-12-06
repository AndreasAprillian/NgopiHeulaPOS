package com.example.NgopiHeula.repository;

import com.example.NgopiHeula.model.dto.order.OrderAllDTO;
import com.example.NgopiHeula.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer > {

    @Query("""
			SELECT new com.example.NgopiHeula.model.dto.order.OrderAllDTO(ord.invoiceNumber, ord.username, ord.clientName, ord.orderDate)
			FROM Order AS ord
			WHERE (:username IS NULL OR ord.username LIKE %:username%)
			AND (:clientName IS NULL OR ord.clientName LIKE %:clientName%) """)
    public List<OrderAllDTO> getAllProduct(@Param("username") String username,@Param("clientName") String clientName);
}
