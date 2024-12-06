package com.example.NgopiHeula.service.abstraction;

import com.example.NgopiHeula.model.dto.category.CategoryUpsertDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    public ResponseEntity<Object> getListCategory(String name);

    public ResponseEntity<Object> getCategorybyId(Integer id);

    public void save(CategoryUpsertDTO dto);

    public Boolean delete(Integer id);

    public Long dependentProducts(Integer id);

    public boolean checkExistingCategoryName(Integer idValue, String nameValue);
}
