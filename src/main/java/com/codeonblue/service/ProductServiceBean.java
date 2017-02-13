package com.codeonblue.service;

import com.codeonblue.model.Product;
import com.codeonblue.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductServiceBean implements ProductService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceBean(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Collection<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product find(Long id) {
        return productRepository.findOne(id);
    }
}
