package com.codeonblue.controller;

import com.codeonblue.builder.ProductBuilder;
import com.codeonblue.model.Product;
import com.codeonblue.service.ProductService;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class)
public class HomeControllerTest extends AbstractControllerTest{

    @MockBean
    ProductService productServiceMock;

    @Test
    public void testShouldForwardToHomePageWhenAccessingRoot() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    public void testShouldForwardToProductListPageWithProductListAttached() throws Exception {
        Product product1 = new ProductBuilder()
                        .id(1L)
                        .category("Sport")
                        .description("Soccer ball")
                        .price(50.0)
                        .condition("condition1")
                        .status("Available")
                        .unitStock(50)
                        .manufacturer("Jackson Sports")
                        .createProduct();

        Product product2 = new ProductBuilder()
                        .id(2L)
                        .category("Sport")
                        .description("Soccer Socks")
                        .price(5.0)
                        .condition("condition2")
                        .status("Available")
                        .unitStock(100)
                        .manufacturer("Jason Sports")
                        .createProduct();

        when(productServiceMock.findAll()).thenReturn(Arrays.asList(product1, product2));

        mockMvc.perform(get("/productList"))
                .andExpect(status().isOk())
                .andExpect(view().name("productList"))
                .andExpect(model().attribute("productList", hasSize(2)))
                .andExpect(model().attribute("productList", hasItem(
                    allOf(
                            hasProperty("id", is(1L)),
                            hasProperty("category", is("Sport")),
                            hasProperty("description", is("Soccer ball"))
                    )
                )))
                .andExpect(model().attribute("productList", hasItem(
                    allOf(
                            hasProperty("id", is(2L)),
                            hasProperty("category", is("Sport")),
                            hasProperty("description", is("Soccer Socks"))
                    )
                )));

        verify(productServiceMock, times(1)).findAll();
        verifyNoMoreInteractions(productServiceMock);
    }

}
