package com.example.NgopiHeula.rest;

import com.example.NgopiHeula.model.dto.order.OrderUpsertDTO;
import com.example.NgopiHeula.model.response.ErrorValidationRest;
import com.example.NgopiHeula.service.abstraction.OrderService;
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
@RequestMapping("/api/order")
public class RestOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ResponsesHelper responsesHelper;

    @GetMapping
    public ResponseEntity<Object> getAllOrder(@RequestParam(required = false) String username,
                                                   @RequestParam(required = false) String clientName){
        try{
            return orderService.getAllOrder(username,clientName);
        }catch (Exception e){
            return responsesHelper.statusInternalError(e.getMessage());
        }
    }
    @GetMapping("/getAccountDropdown")
    public ResponseEntity<Object> getAccountDropdown(){
        try{
            return orderService.getAccountDropdown();
        } catch (Exception e){
            return responsesHelper.statusInternalError(e.getMessage());
        }
    }

    @GetMapping("/{invoiceNumber}")
    public ResponseEntity<Object> getUpdate(@PathVariable(required = true) Integer invoiceNumber){
        try{
            return orderService.getOrderbyInvoiceNumber(invoiceNumber);
        } catch (Exception e){
            return responsesHelper.statusInternalError(e.getMessage());
        }
    }
    @PostMapping("")
    public ResponseEntity<Object> insertNewProduct(@Valid @RequestBody OrderUpsertDTO dto,
                                                   BindingResult bindingResult){
        try {
            if (!bindingResult.hasErrors()) {
                orderService.save(dto);
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
    public ResponseEntity<Object> updateNewProduct(@Valid @RequestBody OrderUpsertDTO dto,
                                                   BindingResult bindingResult) {
        try {
            if (!bindingResult.hasErrors()) {
                orderService.save(dto);
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
    @DeleteMapping("/{invoiceNumber}")
    public ResponseEntity<Object> delete(@PathVariable(required = true)int invoiceNumber){
        try{
            orderService.delete(invoiceNumber);
            return responsesHelper.statusOk();
        }catch (Exception e){
            return responsesHelper.statusInternalError(e.getMessage());
        }
    }
}
