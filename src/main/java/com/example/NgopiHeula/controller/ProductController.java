package com.example.NgopiHeula.controller;

import com.example.NgopiHeula.model.dto.dropdown.DropdownDTO;
import com.example.NgopiHeula.model.dto.product.ProductUpsertDTO;
import com.example.NgopiHeula.model.response.GenericResponse;
import com.example.NgopiHeula.service.abstraction.ProductService;
import com.example.NgopiHeula.utility.DropdownHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/index")
    public String index(Model model, @RequestParam(defaultValue = "") String name,
                        @RequestParam(defaultValue = "") Integer categoryId){
        GenericResponse<List<Object>> listGenericResponse = (GenericResponse<List<Object>>) productService.getAllProduct(name,categoryId).getBody();
        GenericResponse<List<Object>> listGenericDropdown = (GenericResponse<List<Object>>) productService.getCategoryDropdown().getBody();
        model.addAttribute("breadCrumbs", "product");
        model.addAttribute("grid",listGenericResponse.getData());
        model.addAttribute("categoryDropdown",listGenericDropdown.getData());
        model.addAttribute("productName", name);
        model.addAttribute("categoryId", categoryId);
        return "product/product-index";
    }
    @GetMapping("/upsertForm")
    public String upsertForm(@RequestParam(required = false) Integer productId, Model model) {
        GenericResponse<List<Object>> listGenericDropdown = (GenericResponse<List<Object>>) productService.getCategoryDropdown().getBody();
        List<DropdownDTO> dataAvailable = DropdownHelper.isAvailable();
        if (productId != null) {
            GenericResponse<Object> genericResponse = (GenericResponse<Object>) productService.getProductbyId(productId).getBody();
            ProductUpsertDTO dto = (ProductUpsertDTO) genericResponse.getData();
            model.addAttribute("dto",dto);
            model.addAttribute("categoryDropdown",listGenericDropdown.getData());
            model.addAttribute("availableDropdown",dataAvailable);
            model.addAttribute("breadCrumbs", "Update Product");
            model.addAttribute("categoryId", dto.getCategoryId());
            model.addAttribute("isAvailable", dto.getIsAvailable());
        } else {
            ProductUpsertDTO dto = new ProductUpsertDTO();
            model.addAttribute("breadCrumbs", "Insert Product");
            model.addAttribute("categoryDropdown",listGenericDropdown.getData());
            model.addAttribute("availableDropdown",dataAvailable);
            model.addAttribute("dto",dto);
            model.addAttribute("categoryId", dto.getCategoryId());
            model.addAttribute("isAvailable", dto.getIsAvailable());
        }
        return "product/product-form";
    }
    @PostMapping("/upsert")
    public String upsert(@Valid @ModelAttribute("dto") ProductUpsertDTO dto,
                         BindingResult bindingResult, Model model) {
        GenericResponse<List<Object>> listGenericDropdown = (GenericResponse<List<Object>>) productService.getCategoryDropdown().getBody();
        List<DropdownDTO> dataAvailable = DropdownHelper.isAvailable();
        if (bindingResult.hasErrors()) {
            model.addAttribute("dto", dto);
            model.addAttribute("categoryDropdown",listGenericDropdown.getData());
            model.addAttribute("availableDropdown",dataAvailable);
            model.addAttribute("breadCrumbs", "Insert or Update Product has Error");
            model.addAttribute("categoryId", dto.getCategoryId());
            model.addAttribute("isAvailable", dto.getIsAvailable());
            return "product/product-form";
        } else {
            productService.save(dto);
            return "redirect:/product";
        }
    }
    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) Integer productId, Model model) {
        long dependentProducts = productService.dependentOrderDetails(productId);
        if(dependentProducts > 0) {
            model.addAttribute("dependencies", dependentProducts);
            model.addAttribute("breadCrumbs", "Product Index / Fail to Delete Category");
            return "product/product-delete";
        }
        productService.delete(productId);
        return "redirect:/product/index";
    }
}
