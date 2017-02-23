package com.codeonblue.service;

import com.codeonblue.AbstractTest;
import com.codeonblue.builder.ProductBuilder;
import com.codeonblue.model.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@Transactional
public class ProductServiceTest extends AbstractTest{

    @Autowired
    private ProductService productService;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testFindAllProducts() throws Exception {
        Collection<Product> productList = productService.findAll();

        assertNotNull("failure - expected not null", productList);
        assertEquals("failure - expected size", 3, productList.size());

        assertThat(productList, hasItem(
                allOf(
                        hasProperty("id", is(1L)),
                        hasProperty("name", is("Guitar1")),
                        hasProperty("description", is("This is a fender strat guitar")),
                        hasProperty("category", is("Instrument")),
                        hasProperty("price", is(1200.0)),
                        hasProperty("unitStock", is(11)),
                        hasProperty("status", is("Active")),
                        hasProperty("condition", is("new")),
                        hasProperty("manufacturer", is("Fender"))
                )
        ));

        assertThat(productList, hasItem(
                allOf(
                        hasProperty("id", is(2L)),
                        hasProperty("name", is("Record1")),
                        hasProperty("description", is("This is an awesome mix of 20th century")),
                        hasProperty("category", is("Record")),
                        hasProperty("price", is(25.0)),
                        hasProperty("unitStock", is(51)),
                        hasProperty("status", is("Active")),
                        hasProperty("condition", is("new")),
                        hasProperty("manufacturer", is("EMI"))
                )
        ));

        assertThat(productList, hasItem(
                allOf(
                        hasProperty("id", is(3L)),
                        hasProperty("name", is("Speaker1")),
                        hasProperty("category", is("Accessory")),
                        hasProperty("description", is("This is Polk Shelf Speaker!")),
                        hasProperty("price", is(355.0)),
                        hasProperty("condition", is("new")),
                        hasProperty("status", is("Active")),
                        hasProperty("unitStock", is(9)),
                        hasProperty("manufacturer", is("Polk"))
                )
        ));
    }

    @Test
    public void testFindOneProduct() {
        Product product = productService.find(1L);
        assertNotNull("faiulre - expected not null", product);
        assertThat(product.getId(), is(1L));
        assertThat(product.getName(), is("Guitar1"));
        assertThat(product.getDescription(), is("This is a fender strat guitar"));
        assertThat(product.getCategory(), is("Instrument"));
        assertThat(product.getPrice(), is(1200.0));
        assertThat(product.getUnitStock(), is(11));
        assertThat(product.getStatus(), is("Active"));
        assertThat(product.getCondition(), is("new"));
        assertThat(product.getManufacturer(), is("Fender"));
    }

    @Test
    public void testCreateProduct() {
        Product productToBeCreated = new ProductBuilder()
                .name("Product Test 01")
                .description("Product description 01")
                .category("Product category 01")
                .price(30.0)
                .condition("Condition1")
                .status("Not Available")
                .unitStock(100)
                .manufacturer("Jackson Sports")
                .createProductWithoutId();


        Product productCreated = productService.add(productToBeCreated);
        assertNotNull("Failure - expected not null", productCreated);
        assertThat(productCreated.getName(), is("Product Test 01"));
        assertThat(productCreated.getDescription(), is("Product description 01"));
        assertThat(productCreated.getCategory(), is("Product category 01"));
        assertThat(productCreated.getPrice(), is(30.0));
        assertThat(productCreated.getCondition(), is("Condition1"));
        assertThat(productCreated.getStatus(), is("Not Available"));
        assertThat(productCreated.getUnitStock(), is(100));
        assertThat(productCreated.getManufacturer(), is("Jackson Sports"));
    }
}