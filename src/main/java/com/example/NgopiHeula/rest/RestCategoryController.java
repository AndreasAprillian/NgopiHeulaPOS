package com.example.NgopiHeula.rest;

import com.example.NgopiHeula.model.dto.category.CategoryUpsertDTO;
import com.example.NgopiHeula.model.response.ErrorValidationRest;
import com.example.NgopiHeula.service.abstraction.CategoryService;
import com.example.NgopiHeula.utility.ResponsesHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class RestCategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ResponsesHelper responsesHelper;

    @GetMapping
    public ResponseEntity<Object> getProductbyName(@RequestParam(required = false) String name){

        try {
            return categoryService.getListCategory(name);
        } catch (Exception ex) {
            return responsesHelper.statusInternalError(ex.getMessage());
        }
    }

   @GetMapping("/{id}")
    public ResponseEntity<Object> getUpdate(@PathVariable(required = true)Integer id){
       try {
           return categoryService.getCategorybyId(id);
       } catch (Exception ex) {
           return responsesHelper.statusInternalError(ex.getMessage());
       }
    }

    @PostMapping("")
    public ResponseEntity<Object> insertNewCategory(@Valid @RequestBody CategoryUpsertDTO dto,
                                       BindingResult bindingResult){
        try {
            if (!bindingResult.hasErrors()) {
                categoryService.save(dto);
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
        }catch (Exception ex){
            return responsesHelper.statusInternalError(ex.getMessage());

        }
    }
    @PutMapping("")
    public ResponseEntity<Object> updateNewCategory(@Valid @RequestBody CategoryUpsertDTO dto,
                                                    BindingResult bindingResult){
        try {
            if (!bindingResult.hasErrors()) {
                categoryService.save(dto);
                return responsesHelper.statusOk(dto);
            }
            else {
                List<ErrorValidationRest> errorList = new ArrayList<>();
                for (ObjectError error : bindingResult.getAllErrors()) {
                    FieldError errs = (FieldError) error;
                    errorList.add(new ErrorValidationRest(errs.getField(),errs.getDefaultMessage()));
                }
                return responsesHelper.statusUnprosesable(errorList);
            }
        }catch (Exception ex){
            return responsesHelper.statusInternalError(ex.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(required = true)int id){
        try{
            categoryService.delete(id);
            return responsesHelper.statusOk();
        }catch (Exception e){
            return responsesHelper.statusInternalError(e.getMessage());
        }
    }
}
