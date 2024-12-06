package com.example.NgopiHeula.service.implementation;

import com.example.NgopiHeula.model.dto.dropdown.DropdownAltDTO;
import com.example.NgopiHeula.model.dto.product.ProductAllDTO;
import com.example.NgopiHeula.model.dto.product.ProductUpsertDTO;
import com.example.NgopiHeula.model.entity.Product;
import com.example.NgopiHeula.repository.CategoryRepository;
import com.example.NgopiHeula.repository.OrderDetailRepository;
import com.example.NgopiHeula.repository.ProductRepository;
import com.example.NgopiHeula.service.abstraction.ProductService;
import com.example.NgopiHeula.utility.ResponsesHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ResponsesHelper responsesHelper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public ResponseEntity<Object> getAllProduct(String name, Integer category) {
        List<ProductAllDTO> productAllDTOList = productRepository.getAllProduct(name, category);
        if (!productAllDTOList.isEmpty()) {
            return responsesHelper.statusOk(productAllDTOList);
        }
        return responsesHelper.statusNotFound();
    }

    @Override
    public ResponseEntity<Object> getCategoryDropdown() {
        List<DropdownAltDTO> objectList = categoryRepository.findAllOrderByName();
        if (!objectList.isEmpty()) {
            return responsesHelper.statusOk(objectList);
        }
        return responsesHelper.statusNotFound();
    }

    @Override
    public ResponseEntity<Object> getProductbyId(Integer id) {
        Product product = productRepository.findById(id).get();
        ProductUpsertDTO dto = new ProductUpsertDTO(product.getProductId(),product.getCategoryId(), product.getName(), product.getPrice(), product.getIsAvailable());
        if (dto != null) {
            return responsesHelper.statusOk(dto);
        }
        return responsesHelper.statusNotFound();
    }

    @Override
    public void save(ProductUpsertDTO dto) {
        Product product = new Product(dto.getProductId(),dto.getCategoryId(),dto.getName(), dto.getPrice(), dto.getIsAvailable());
        productRepository.save(product);
    }

    @Override
    public Boolean delete(int id) {
        if(dependentOrderDetails(id) == 0) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Long dependentOrderDetails(Integer id){
        return orderDetailRepository.countByProduct(id);
    }
}
