package com.codeonblue.service;

import com.codeonblue.builder.ProductBuilder;
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

        Product product1 = new ProductBuilder()
                .name("Guitar1")
                .category("Instrument")
                .description("This is a fender strat guitar")
                .price(1200)
                .condition("new")
                .status("Active")
                .unitStock(11)
                .manufacturer("Fender")
                .createProductWithoutId();
        productRepository.save(product1);

        Product product2 = new ProductBuilder()
                .name("Record1")
                .category("Record")
                .description("This is an awesome mix of 20th century")
                .price(25)
                .condition("new")
                .status("Active")
                .unitStock(51)
                .manufacturer("EMI")
                .createProductWithoutId();
        productRepository.save(product2);

        Product product3 = new ProductBuilder()
                .name("Speaker1")
                .category("Accessory")
                .description("This is Polk Shelf Speaker!")
                .price(355)
                .condition("new")
                .status("Active")
                .unitStock(9)
                .manufacturer("Polk")
                .createProductWithoutId();
        productRepository.save(product3);


    }


}
