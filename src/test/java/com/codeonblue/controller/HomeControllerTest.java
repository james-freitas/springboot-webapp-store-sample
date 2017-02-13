package com.codeonblue.controller;

import com.codeonblue.builder.ProductBuilder;
import com.codeonblue.model.Product;
import com.codeonblue.service.ProductService;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

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

        when(productServiceMock.findAll()).thenReturn(createListWithTwoProducts());

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

    private List<Product> createListWithTwoProducts() {
        Product product1 = new ProductBuilder()
                .id(1L)
                .category("Sport")
                .description("Soccer ball")
                .price(50.0)
                .condition("condition1")
                .status("Available")
                .unitStock(50)
                .manufacturer("Jackson Sports")
                .createProductWithId();

        Product product2 = new ProductBuilder()
                        .id(2L)
                        .category("Sport")
                        .description("Soccer Socks")
                        .price(5.0)
                        .condition("condition2")
                        .status("Available")
                        .unitStock(100)
                        .manufacturer("Jason Sports")
                        .createProductWithId();
        return Arrays.asList(product1, product2);
    }

    @Test
    public void testShouldForwardToProductDetailPageWithProductAttached() throws Exception {
        when(productServiceMock.find(1L)).thenReturn(createProduct());

        mockMvc.perform(get("/productList/viewProduct"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewProduct"))
                .andExpect(model().attribute("productInstance", hasProperty("id", is(1L))))
                .andExpect(model().attribute("productInstance", hasProperty("category", is("Sport"))))
                .andExpect(model().attribute("productInstance", hasProperty("description", is("Soccer ball"))))
                .andExpect(model().attribute("productInstance", hasProperty("price", is(50.0))))
                .andExpect(model().attribute("productInstance", hasProperty("condition", is("condition1"))))
                .andExpect(model().attribute("productInstance", hasProperty("status", is("Available"))))
                .andExpect(model().attribute("productInstance", hasProperty("unitStock", is(50))))
                .andExpect(model().attribute("productInstance", hasProperty("manufacturer", is("Jackson Sports"))));

        verify(productServiceMock, times(1)).find(1L);
        verifyNoMoreInteractions(productServiceMock);

    }

    private Product createProduct() {
        return new ProductBuilder()
                .id(1L)
                .category("Sport")
                .description("Soccer ball")
                .price(50.0)
                .condition("condition1")
                .status("Available")
                .unitStock(50)
                .manufacturer("Jackson Sports")
                .createProductWithId();
    }

}
