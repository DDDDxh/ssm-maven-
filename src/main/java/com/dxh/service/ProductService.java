package com.dxh.service;

import com.dxh.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(String id);

    boolean edit(Product product);
}
