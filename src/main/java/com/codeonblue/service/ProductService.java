package com.codeonblue.service;

import com.codeonblue.model.Product;

import java.util.Collection;

public interface ProductService {

    Collection<Product> findAll();

    Product find(Long id);
}
