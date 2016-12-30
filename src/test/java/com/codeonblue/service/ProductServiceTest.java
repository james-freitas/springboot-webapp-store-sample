package com.codeonblue.service;

import com.codeonblue.AbstractTest;
import com.codeonblue.model.Product;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

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
    public void testFindAll() throws Exception {
        Collection<Product> list = productService.findAll();

        Assert.assertNotNull("failure - expected not null", list);
        Assert.assertEquals("failure - expected size", 2, list.size());
    }

}