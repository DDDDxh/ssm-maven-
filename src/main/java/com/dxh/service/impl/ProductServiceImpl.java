package com.dxh.service.impl;

import com.dxh.dao.ProductMapper;
import com.dxh.entity.Product;
import com.dxh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findAll() {

        List<Product> products = productMapper.selectByExampleWithBLOBs(null);
        return products;
    }

    @Override
    public Product findById(String id) {
        Product product = productMapper.selectByPrimaryKey(Integer.parseInt(id));
        return product;
    }

    @Override
    public boolean edit(Product product) {
        int i = productMapper.updateByPrimaryKeySelective(product);
        if(i > 0 ){
            return true;
        }
        return false;
    }
}
