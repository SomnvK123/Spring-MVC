package com.example.spring_mvc.controller;


import com.example.spring_mvc.dto.ProductDTO;
import com.example.spring_mvc.exception.BadRequestException;
import com.example.spring_mvc.exception.NoDataFoundException;
import com.example.spring_mvc.model.Product;
import com.example.spring_mvc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAll();
        if (products.isEmpty()) {
            throw new NoDataFoundException("Không có sản phẩm nào.");
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Map<String, Object>> getProductByName(@PathVariable String name) {
        try {
            Product product = productService.findByName(name);
            List<String> msg = new ArrayList<>();
            msg.add("Get by name successful");
            Map<String, Object> response = new HashMap<>();
            response.put("messages", msg);
            response.put("product", product);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (NoSuchElementException e) {
            List<String> msg = new ArrayList<>();
            msg.add("Get by name failed: NOT FOUND");
            Map<String, Object> response = new HashMap<>();
            response.put("messages", msg);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (IllegalArgumentException e) {
            List<String> msg = new ArrayList<>();
            msg.add("Get by name failed: BAD REQUEST");
            Map<String, Object> response = new HashMap<>();
            response.put("messages", msg);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        } catch (HttpServerErrorException e) {
            List<String> msg = new ArrayList<>();
            msg.add("Get by name failed: INTERNAL SERVER ERROR");
            Map<String, Object> response = new HashMap<>();
            response.put("messages", msg);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }
    }

    @PostMapping("/update")
    public ResponseEntity<Map<String, Object>> updateProduct(String name, String description, double price, String category) {
        try {
            productService.updateProduct(name, description, price, category);
            List<String> msg = new ArrayList<>();
            msg.add("Update successful");
            Map<String, Object> response = new HashMap<>();
            response.put("messages", msg);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (NoSuchElementException e) {
            List<String> msg = new ArrayList<>();
            msg.add("Update failed: NOT FOUND");
            Map<String, Object> response = new HashMap<>();
            response.put("messages", msg);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (IllegalArgumentException e) {
            List<String> msg = new ArrayList<>();
            msg.add("Update failed: BAD REQUEST");
            Map<String, Object> response = new HashMap<>();
            response.put("messages", msg);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        } catch (HttpServerErrorException e) {
            List<String> msg = new ArrayList<>();
            msg.add("Update failed: INTERNAL SERVER ERROR");
            Map<String, Object> response = new HashMap<>();
            response.put("messages", msg);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }
    }

    @PostMapping("/insert")
    public ResponseEntity<Map<String, Object>> insertProduct(@RequestBody ProductDTO productDTO) {
        try {
            productService.insertProduct(productDTO);
            List<String> msg = new ArrayList<>();
            msg.add("Insert successful");
            Map<String, Object> response = new HashMap<>();
            response.put("messages", msg);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (NoSuchElementException e) {
            List<String> msg = new ArrayList<>();
            msg.add("Insert failed: NOT FOUND");
            Map<String, Object> response = new HashMap<>();
            response.put("messages", msg);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (IllegalArgumentException e) {
            List<String> msg = new ArrayList<>();
            msg.add("Insert failed: BAD REQUEST");
            Map<String, Object> response = new HashMap<>();
            response.put("messages", msg);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        } catch (HttpServerErrorException e) {
            List<String> msg = new ArrayList<>();
            msg.add("Insert failed: INTERNAL SERVER ERROR");
            Map<String, Object> response = new HashMap<>();
            response.put("messages", msg);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable int id) {
        try {
            productService.softDelete(id);
            List<String> msg = new ArrayList<>();
            msg.add("Delete successful");
            Map<String, Object> response = new HashMap<>();
            response.put("messages", msg);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (NoSuchElementException e) {
            List<String> msg = new ArrayList<>();
            msg.add("Delete failed: NOT FOUND");
            Map<String, Object> response = new HashMap<>();
            response.put("messages", msg);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (IllegalArgumentException e) {
            List<String> msg = new ArrayList<>();
            msg.add("Delete failed: BAD REQUEST");
            Map<String, Object> response = new HashMap<>();
            response.put("messages", msg);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        } catch (HttpServerErrorException e) {
            List<String> msg = new ArrayList<>();
            msg.add("Delete failed: INTERNAL SERVER ERROR");
            Map<String, Object> response = new HashMap<>();
            response.put("messages", msg);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }
    }
}
