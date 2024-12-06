package com.example.NgopiHeula.repository;

import com.example.NgopiHeula.model.dto.dropdown.DropdownDTO;
import com.example.NgopiHeula.model.dto.product.ProductAllDTO;
import com.example.NgopiHeula.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository <Product, Integer>{

    @Query("""
			SELECT COUNT(pro.productId)
			FROM Product AS pro
			WHERE pro.categoryId = :Id """)
    public Long countByCategory(@Param("Id")int id);

    @Query("""
			SELECT new com.example.NgopiHeula.model.dto.product.ProductAllDTO(pro.productId, pro.name, cat.name, pro.price, pro.isAvailable)
			FROM Product AS pro
				INNER JOIN pro.category AS cat
			WHERE (:name IS NULL OR pro.name LIKE %:name%)
			AND (:categoryId IS NULL OR pro.categoryId = :categoryId) """)
    public List<ProductAllDTO> getAllProduct(@Param("name") String name, @Param("categoryId") Integer categoryId);

	@Query("""
			SELECT pro.id, pro.name
			FROM Product AS pro
			ORDER By pro.name """)
	public List<DropdownDTO> findAllOrderByName();
}
