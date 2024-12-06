package com.example.NgopiHeula.rest;

import com.example.NgopiHeula.model.dto.category.CategoryUpsertDTO;
import com.example.NgopiHeula.model.dto.product.ProductUpsertDTO;
import com.example.NgopiHeula.model.response.ErrorValidationRest;
import com.example.NgopiHeula.service.abstraction.ProductService;
import com.example.NgopiHeula.utility.ResponsesHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class RestProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ResponsesHelper responsesHelper;

    @GetMapping
    public ResponseEntity<Object> getProductbyName(@RequestParam(required = false) String name,
                                                   @RequestParam(required = false) Integer categoryId){
        try{
            return productService.getAllProduct(name,categoryId);
        }catch (Exception e){
            return responsesHelper.statusInternalError(e.getMessage());
        }
    }

    @GetMapping("/categoryDropdown")
    public ResponseEntity<Object> getCategoryDropdown(){
        try{
            return productService.getCategoryDropdown();
        } catch (Exception e){
            return responsesHelper.statusInternalError(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUpdate(@PathVariable(required = true) Integer id){
        try{
            return productService.getProductbyId(id);
        } catch (Exception e){
            return responsesHelper.statusInternalError(e.getMessage());
        }
    }
    @PostMapping("")
    public ResponseEntity<Object> insertNewProduct(@Valid @RequestBody ProductUpsertDTO dto,
                                                    BindingResult bindingResult){
        try {
            if (!bindingResult.hasErrors()) {
                productService.save(dto);
                return responsesHelper.statusCreated(dto);
            }
            else {
                List<ErrorValidationRest> errorList = new ArrayList<>();
                for (ObjectError error : bindingResult.getAllErrors()) {
                    FieldError errs = (FieldError) error;
                    errorList.add(new ErrorValidationRest(errs.getField(),errs.getDefaultMessage()));
                }
                return responsesHelper.statusUnprosesable(errorList);
            }
        }catch (Exception e){
            return responsesHelper.statusInternalError(e.getMessage());

        }
    }
    @PutMapping("")
    public ResponseEntity<Object> updateNewProduct(@Valid @RequestBody ProductUpsertDTO dto,
                                                   BindingResult bindingResult) {
        try {
            if (!bindingResult.hasErrors()) {
                productService.save(dto);
                return responsesHelper.statusOk(dto);
            } else {
                List<ErrorValidationRest> errorList = new ArrayList<>();
                for (ObjectError error : bindingResult.getAllErrors()) {
                    FieldError errs = (FieldError) error;
                    errorList.add(new ErrorValidationRest(errs.getField(), errs.getDefaultMessage()));
                }
                return responsesHelper.statusUnprosesable(errorList);
            }
        } catch (Exception e) {
            return responsesHelper.statusInternalError(e.getMessage());

        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(required = true)int id){
        try{
            productService.delete(id);
            return responsesHelper.statusOk();
        }catch (Exception e){
            return responsesHelper.statusInternalError(e.getMessage());
        }
    }
}
