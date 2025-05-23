package com.example.spring_mvc.controller;


import com.example.spring_mvc.dto.ProductDTO;
import com.example.spring_mvc.model.Product;
import com.example.spring_mvc.service.ProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id, asc") String[] sort) {
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
        Page<Product> products = productService.findAll(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        Product product = productService.findByName(name);
            return ResponseEntity.ok(product);
    }

    @Transactional
    @PostMapping("/update/{name}")
    public ResponseEntity<Void> updateProduct(@Valid @PathVariable String name, @RequestBody ProductDTO productDTO) {
        productService.updateProduct(name, productDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Transactional
    @PostMapping("/insert")
    public ResponseEntity<Void> insertProduct(@Valid @RequestBody ProductDTO productDTO) {
        productService.insertProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> softDeleteProduct(@PathVariable int id) {
        productService.softDelete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Transactional
    @PostMapping("/batchInsert")
    public ResponseEntity<Void> batchInsertProducts(@RequestBody List<Product> products) {
        productService.batchInsertProducts(products);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/filter/{category}")
    public ResponseEntity<List<Product>> filterByCategory(@PathVariable String category) {
        productService.filterByCategory(category);
        return ResponseEntity.ok(productService.filterByCategory(category));
    }
}