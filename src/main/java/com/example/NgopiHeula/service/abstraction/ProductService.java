package com.example.NgopiHeula.service.abstraction;

import com.example.NgopiHeula.model.dto.product.ProductUpsertDTO;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    public ResponseEntity<Object> getAllProduct(String name, Integer category);

    public ResponseEntity<Object> getCategoryDropdown();

    public ResponseEntity<Object> getProductbyId(Integer id);

    public void save(ProductUpsertDTO dto);

    public Boolean delete(int id);

    public Long dependentOrderDetails(Integer id);
}
