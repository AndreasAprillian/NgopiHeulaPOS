package com.example.NgopiHeula.controller;

import com.example.NgopiHeula.model.dto.dropdown.DropdownDTO;
import com.example.NgopiHeula.model.dto.order.OrderDetailDTO;
import com.example.NgopiHeula.model.dto.order.OrderUpsertDTO;
import com.example.NgopiHeula.model.dto.product.ProductUpsertDTO;
import com.example.NgopiHeula.model.response.GenericResponse;
import com.example.NgopiHeula.service.abstraction.OrderService;
import com.example.NgopiHeula.utility.DropdownHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/index")
    public String getAllOrder(Model model,@RequestParam(required = false) String username,
                              @RequestParam(required = false) String clientName){
        GenericResponse<List<Object>> listGenericResponse = (GenericResponse<List<Object>>) orderService.getAllOrder(username,clientName).getBody();
        GenericResponse<List<Object>> listGenericDropdown = (GenericResponse<List<Object>>) orderService.getAccountDropdown().getBody();
        model.addAttribute("breadCrumbs", "Order");
        model.addAttribute("grid",listGenericResponse.getData());
        model.addAttribute("cashierDropdown",listGenericDropdown.getData());
        model.addAttribute("username", username);
        model.addAttribute("clientName", clientName);
        return "order/order-index";
    }
    @GetMapping("/upsertForm")
    public String upsertForm(@RequestParam(required = false) Integer invoiceNumber, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Mendapatkan username login
        if (invoiceNumber != null) {
            GenericResponse<Object> genericResponse = (GenericResponse<Object>) orderService.getOrderbyInvoiceNumber(invoiceNumber).getBody();
            OrderUpsertDTO dto = (OrderUpsertDTO) genericResponse.getData();
            model.addAttribute("dto",dto);
            model.addAttribute("breadCrumbs", "Update Order");
            model.addAttribute("loggedInUsername", username);
        } else {
            OrderUpsertDTO dto = new OrderUpsertDTO();
            model.addAttribute("breadCrumbs", "Insert Order");
            model.addAttribute("dto",dto);
            model.addAttribute("loggedInUsername", username);
        }
        return "order/order-form";
    }
    @PostMapping("/upsert")
    public String upsert(@Valid @ModelAttribute("dto") OrderUpsertDTO dto,
                         BindingResult bindingResult, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Mendapatkan username login
        dto.setUsername(username);
        if (bindingResult.hasErrors()) {
            model.addAttribute("dto", dto);
            model.addAttribute("breadCrumbs", "Insert or Update Product has Error");
            model.addAttribute("loggedInUsername", username);
            return "order/order-form";
        } else {
            orderService.save(dto);
            return "redirect:/order";
        }
    }
    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) Integer invoiceNumber, Model model) {
        try{
            orderService.delete(invoiceNumber);
        }catch (Exception e){
            model.addAttribute("breadCrumbs", "Product Index / Fail to Delete Category");
        }
        return "redirect:/order/index";
    }
    @GetMapping("/detail")
    public String orderDetail(@RequestParam(required = false) Integer invoiceNumber, Model model) {
            GenericResponse<Object> genericResponse = (GenericResponse<Object>) orderService.getOrderbyInvoiceNumber(invoiceNumber).getBody();
            List<OrderDetailDTO> detailDTOList = orderService.getDetailOrder(invoiceNumber);
            OrderUpsertDTO header = (OrderUpsertDTO) genericResponse.getData();
            model.addAttribute("header",header);
            model.addAttribute("grid",detailDTOList);
            model.addAttribute("breadCrumbs", "Detail Order");

        return "order/order-detail";
    }
}
