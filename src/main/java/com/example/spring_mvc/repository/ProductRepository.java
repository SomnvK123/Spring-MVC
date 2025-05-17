package com.example.spring_mvc.repository;


import com.example.spring_mvc.dto.ProductDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.spring_mvc.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select p from Product p where p.name = ?1")
    Product findByName(String name);

    @Query("select p from Product p where p.isDeleted = false")
    List<Product> findAll();

    @Modifying
    @Transactional
    @Query("update Product p set p.name = ?1, p.description = ?2, p.price = ?3, p.category = ?4 where p.id = ?5")
    void updateProduct(String name, String description, double price, String category);

    @Modifying
    @Transactional
    @Query("insert into Product (name, description, price, category) values (?1, ?2, ?3, ?4)")
    void insertProduct(ProductDTO productDTO);

    @Query("update Product p set p.isDeleted = true where p.id = ?1")
    void softDelete(int id);
}
