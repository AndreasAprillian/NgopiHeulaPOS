package com.example.NgopiHeula.repository;

import com.example.NgopiHeula.model.dto.order.OrderDetailDTO;
import com.example.NgopiHeula.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    @Query("""
			SELECT COUNT(ordet.id) 
			FROM OrderDetail AS ordet
			WHERE ordet.productId = :productId""")
    public Long countByProduct(@Param("productId") int productId);

	@Query("""
			SELECT new com.example.NgopiHeula.model.dto.order.OrderDetailDTO(ordet.orderDetailId, ordet.invoiceNumber, ordet.productId, pro.name, ordet.quantity, ordet.price) 
			FROM OrderDetail AS ordet
			INNER JOIN ordet.product AS pro
			WHERE ordet.invoiceNumber = :invoiceNumber""")
	public List<OrderDetailDTO> getDetailOrderbyInvoice(@Param("invoiceNumber")Integer invoiceNumber);
}
