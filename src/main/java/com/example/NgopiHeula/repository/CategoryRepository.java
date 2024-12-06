package com.example.NgopiHeula.repository;

import com.example.NgopiHeula.model.dto.category.CategoryAllDTO;
import com.example.NgopiHeula.model.dto.dropdown.DropdownAltDTO;
import com.example.NgopiHeula.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query("""
            SELECT new com.example.NgopiHeula.model.dto.category.CategoryAllDTO(cat.categoryId, cat.name, cat.description)
            FROM Category AS cat
            WHERE :name IS NULL OR cat.name LIKE %:name%
            """)
    public List<CategoryAllDTO> getAllCategory(@Param("name") String name);

    @Query("""
			SELECT COUNT(cat.categoryId)
			FROM Category AS cat
			WHERE cat.name = :name AND cat.categoryId != :categoryId""")
    public Integer countByName(@Param("categoryId") Integer categoryId, @Param("name") String name);

    @Query("""
			SELECT new com.example.NgopiHeula.model.dto.dropdown.DropdownAltDTO(cat.id, cat.name)
			FROM Category AS cat
			ORDER By cat.name """)
    public List<DropdownAltDTO> findAllOrderByName();
}
