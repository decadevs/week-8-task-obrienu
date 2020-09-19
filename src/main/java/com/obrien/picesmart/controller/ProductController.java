package com.obrien.picesmart.controller;

import com.obrien.picesmart.services.ProductService;
import com.obrien.picesmart.utils.DTO.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping(path="/product/add/{brandId}")
    public String addProduct(@PathVariable long brandId, @ModelAttribute("addProduct") ProductDTO addProduct){
        productService.saveProduct(addProduct, brandId);
        return "redirect:/brand/" + brandId + "/1";
    }

    @PostMapping(path="/product/edit/{productId}/{brandId}")
    public String editProduct(@PathVariable long brandId, @PathVariable long productId, @ModelAttribute("product") ProductDTO product){
        System.out.println("BEFORE EDIT>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        productService.updateProduct(product, productId);
        return "redirect:/brand/" + brandId + "/1";
    }

    @PostMapping(path = "/product/delete/{productId}/{brandId}")
    public String deleteProduct(@PathVariable long brandId, @PathVariable long productId){
        productService.deleteProduct(productId);
        return "redirect:/brand/" + brandId + "/1";
    }
}
