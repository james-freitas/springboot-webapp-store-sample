package com.codeonblue.controller;

import com.codeonblue.model.Product;
import com.codeonblue.model.ProductImage;
import com.codeonblue.service.ProductService;
import com.codeonblue.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class HomeController {

    private ProductService productService;

    private final StorageService storageService;

    @Autowired
    public HomeController(ProductService productService, StorageService storageService) {
        this.productService = productService;
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/productList")
    public String getProducts(Model model){
        model.addAttribute("productList", productService.findAll());
        return "productList";
    }

    @GetMapping("/productList/viewProduct/{id}")
    public String viewProduct(@PathVariable Long id, Model model){
        model.addAttribute("productInstance", productService.find(id));
        return "viewProduct";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/admin/productInventory")
    public String productInventory(Model model){
        model.addAttribute("productList", productService.findAll());
        return "productInventory";
    }

    @GetMapping("/admin/productInventory/addProduct")
    public String addProduct(Model model){
        Product product = new Product();
        product.setCategory("instrument");
        product.setCondition("new");
        product.setStatus("active");

        model.addAttribute("productInstance", product);

        return "addProduct";
    }

    @PostMapping(value = "/admin/productInventory/addProduct")
    public String addProductPost(@ModelAttribute("productInstance") Product product, MultipartFile file) {

        ProductImage productImage = ProductImage.newInstance(product.getId(), file);

        productService.add(product);
        storageService.storeProductImage(productImage);

        return "redirect:/admin/productInventory";
    }

    @GetMapping("/admin/productInventory/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.remove(id);
        return "redirect:/admin/productInventory";
    }

    @GetMapping("/teste")
    @ResponseBody
    public String teste(HttpServletRequest request){
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");

        return rootDirectory;
    }

}
