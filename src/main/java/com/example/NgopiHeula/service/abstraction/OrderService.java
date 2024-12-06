package com.example.NgopiHeula.service.abstraction;

import com.example.NgopiHeula.model.dto.order.OrderDetailDTO;
import com.example.NgopiHeula.model.dto.order.OrderUpsertDTO;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    public ResponseEntity<Object> getAllOrder(String username, String clientName);

    public ResponseEntity<Object> getOrderbyInvoiceNumber(Integer invoiceNumber);

    public void save(OrderUpsertDTO dto);

    public Boolean delete(int invoiceNumber);

    public ResponseEntity<Object> getAccountDropdown();

    public BigDecimal calculateTotalPrice(BigDecimal price, Integer quantity);

    public List<OrderDetailDTO> getDetailOrder(Integer invoiceNumber);
}
