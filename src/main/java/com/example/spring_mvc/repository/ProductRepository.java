package com.example.spring_mvc.repository;


import com.example.spring_mvc.dto.ProductDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Page<Product> findAll(Pageable pageable);


    @Transactional
    @Modifying
    @Query("update Product p set p.name = ?1, p.description = ?2, p.price = ?3, p.category = ?4 where p.name = ?5")
    void updateProduct(String name, String newName, String description, double price, String category);

    @Transactional
    @Modifying
    @Query("insert into Product (name, description, price, category, isDeleted) values (?1, ?2, ?3, ?4, ?5)")
    void insertProduct(String name, String description, double price, String category, boolean isDeleted);

    @Modifying
    @Query("update Product p set p.isDeleted = true where p.id = ?1")
    void softDelete(int id);


    @Transactional
    @Modifying
    @Query("insert into Product (name, description, price, category) values (?1, ?2, ?3, ?4)")
    void batchInsertProducts(List<Product> products);


    @Query("select p from Product p where p.category = ?1")
    List<Product> filterByCategory(String category);
}
