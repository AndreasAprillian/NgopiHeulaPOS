package com.example.NgopiHeula.service.implementation;

import com.example.NgopiHeula.model.dto.dropdown.DropdownAltDTO;
import com.example.NgopiHeula.model.dto.dropdown.DropdownDTO;
import com.example.NgopiHeula.model.dto.order.OrderAllDTO;
import com.example.NgopiHeula.model.dto.order.OrderDetailDTO;
import com.example.NgopiHeula.model.dto.order.OrderUpsertDTO;
import com.example.NgopiHeula.model.entity.Order;
import com.example.NgopiHeula.repository.AccountRepository;
import com.example.NgopiHeula.repository.OrderDetailRepository;
import com.example.NgopiHeula.repository.OrderRepository;
import com.example.NgopiHeula.service.abstraction.OrderService;
import com.example.NgopiHeula.utility.ResponsesHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImplementation implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ResponsesHelper responsesHelper;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public ResponseEntity<Object> getAllOrder(String username, String clientName) {
        List<OrderAllDTO> orderAllDTOList = orderRepository.getAllProduct(username, clientName);
        if (!orderAllDTOList.isEmpty()) {
            return responsesHelper.statusOk(orderAllDTOList);
        }
        return responsesHelper.statusNotFound();
    }

    @Override
    public ResponseEntity<Object> getOrderbyInvoiceNumber(Integer invoiceNumber) {
        Order order = orderRepository.findById(invoiceNumber).get();
        OrderUpsertDTO dto = new OrderUpsertDTO(order.getInvoiceNumber(), order.getUsername(), order.getClientName(), order.getOrderDate());
        if (dto != null) {
            return responsesHelper.statusOk(dto);
        }
        return responsesHelper.statusNotFound();
    }

    @Override
    public void save(OrderUpsertDTO dto) {
        Order order = new Order(dto.getInvoiceNumber(), dto.getUsername(), dto.getClientName(), LocalDateTime.now());
        orderRepository.save(order);
    }

    @Override
    public Boolean delete(int invoiceNumber) {
        try{
            orderRepository.deleteById(invoiceNumber);
            return true;
        } catch (Exception exception){
            return false;
        }
    }

    @Override
    public ResponseEntity<Object> getAccountDropdown() {
        List<DropdownDTO> objectList = accountRepository.findAllCashier();
        if (!objectList.isEmpty()) {
            return responsesHelper.statusOk(objectList);
        }
        return responsesHelper.statusNotFound();
    }

    @Override
    public BigDecimal calculateTotalPrice(BigDecimal price, Integer quantity) {
        BigDecimal totalPrice = new BigDecimal(quantity).multiply(price);
        return totalPrice;
    }

    @Override
    public List<OrderDetailDTO> getDetailOrder(Integer invoiceNumber) {
        return orderDetailRepository.getDetailOrderbyInvoice(invoiceNumber);
    }
}
