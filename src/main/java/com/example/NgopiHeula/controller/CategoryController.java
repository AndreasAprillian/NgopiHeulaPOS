package com.example.NgopiHeula.controller;

import com.example.NgopiHeula.model.dto.category.CategoryUpsertDTO;
import com.example.NgopiHeula.model.response.GenericResponse;
import com.example.NgopiHeula.service.abstraction.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/index")
    public String index(Model model, @RequestParam(defaultValue = "") String name){
        GenericResponse<List<Object>> listGenericResponse = (GenericResponse<List<Object>>) categoryService.getListCategory(name).getBody();
        model.addAttribute("breadCrumbs", "Category");
        model.addAttribute("grid",listGenericResponse.getData());
        model.addAttribute("categoryName", name);
        return "category/category-index";
    }
    @GetMapping("/upsertForm")
    public String upsertForm(@RequestParam(required = false) Integer categoryId, Model model) {
        if (categoryId != null) {
            GenericResponse<Object> genericResponse = (GenericResponse<Object>) categoryService.getCategorybyId(categoryId).getBody();
            CategoryUpsertDTO dto = (CategoryUpsertDTO) genericResponse.getData();
            model.addAttribute("dto",dto);
            model.addAttribute("breadCrumbs", "Update Category");
        } else {
            CategoryUpsertDTO dto = new CategoryUpsertDTO();
            model.addAttribute("breadCrumbs", "Insert Category");
            model.addAttribute("dto",dto);
        }
        return "category/category-form";
    }
    @PostMapping("/upsert")
    public String upsert(@Valid @ModelAttribute("dto") CategoryUpsertDTO dto,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("dto", dto);
            model.addAttribute("breadCrumbs", "Insert or Update Category has ERROR");
            return "category/category-form";
        } else {
            categoryService.save(dto);
            return "redirect:/category";
        }
    }
    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) Integer categoryId, Model model) {
        long dependentProducts = categoryService.dependentProducts(categoryId);
        if(dependentProducts > 0) {
            model.addAttribute("dependencies", dependentProducts);
            model.addAttribute("breadCrumbs", "Category Index / Fail to Delete Category");
            return "category/category-delete";
        }
        categoryService.delete(categoryId);
        return "redirect:/category/index";
    }
}
