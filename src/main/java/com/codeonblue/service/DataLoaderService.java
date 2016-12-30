package com.codeonblue.service;

import com.codeonblue.model.Product;
import com.codeonblue.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DataLoaderService {

    private final ProductRepository productRepository;

    @Autowired
    public DataLoaderService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    private void loadData() {
        Product p1 = new Product("Canon Ball");
        p1.setCategory("Category 1");
        p1.setCondition("Sold");
        p1.setDescription("Description of the Canon Ball");
        p1.setManufacturer("Unknown");
        p1.setPrice(10.0);
        p1.setStatus("Unavailable");
        p1.setUnitStock(30);
        productRepository.save(p1);

        Product p2 = new Product("Wrecking Ball");
        p2.setCategory("Category 1");
        p2.setCondition("Sold");
        p2.setDescription("Description of the Wrecking Ball");
        p2.setManufacturer("Unknown");
        p2.setPrice(102.0);
        p2.setStatus("Available");
        p2.setUnitStock(30);
        productRepository.save(p2);

    }


}
