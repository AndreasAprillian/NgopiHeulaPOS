package com.example.NgopiHeula.service.implementation;

import com.example.NgopiHeula.model.dto.category.CategoryAllDTO;
import com.example.NgopiHeula.model.dto.category.CategoryUpsertDTO;
import com.example.NgopiHeula.model.entity.Category;
import com.example.NgopiHeula.repository.CategoryRepository;
import com.example.NgopiHeula.repository.ProductRepository;
import com.example.NgopiHeula.service.abstraction.CategoryService;
import com.example.NgopiHeula.utility.ResponsesHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ResponsesHelper responsesHelper;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<Object> getListCategory(String name) {
        List<CategoryAllDTO> categoryAllDTOList = categoryRepository.getAllCategory(name);
        if (!categoryAllDTOList.isEmpty()) {
            return responsesHelper.statusOk(categoryAllDTOList);
        }
        return responsesHelper.statusNotFound();
    }

    @Override
    public ResponseEntity<Object> getCategorybyId(Integer id) {
        Category category = categoryRepository.findById(id).get();
        CategoryUpsertDTO dto = new CategoryUpsertDTO(category.getCategoryId(), category.getName(), category.getDescription());
        if (dto != null) {
            return responsesHelper.statusOk(dto);
        }
        return responsesHelper.statusNotFound();
    }

    @Override
    public void save(CategoryUpsertDTO dto) {
        Category category = new Category(dto.getCategoryId(), dto.getName(), dto.getDescription());
        categoryRepository.save(category);
    }

    @Override
    public Boolean delete(Integer id) {
        if(dependentProducts(id) == 0) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Long dependentProducts(Integer id){
        return productRepository.countByCategory(id);
    }

    @Override
    public boolean checkExistingCategoryName(Integer idValue, String nameValue) {
        Integer id = (idValue == null) ? 0 : idValue;
        var totalData = categoryRepository.countByName(id, nameValue);
        return totalData > 0;
    }

}
