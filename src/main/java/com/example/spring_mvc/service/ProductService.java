package com.example.spring_mvc.service;


import com.example.spring_mvc.dto.ProductDTO;
import com.example.spring_mvc.model.Product;
import com.example.spring_mvc.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Transactional
    public void updateProduct(String name, ProductDTO productDTO) {
        Product product = productRepository.findByName(name);
        if (product != null) {
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setPrice(productDTO.getPrice());
            product.setCategory(productDTO.getCategory());
            productRepository.save(product);
        }
    }


    @Transactional
    public void insertProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(productDTO.getCategory());
        product.setDeleted(false);
        productRepository.save(product);
    }

    public void softDelete(int id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setDeleted(true);
            productRepository.save(product);
        }
    }
}
