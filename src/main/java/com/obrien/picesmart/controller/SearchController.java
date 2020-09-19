package com.obrien.picesmart.controller;

import com.obrien.picesmart.services.ProductService;
import com.obrien.picesmart.utils.DTO.ProductDTO;
import com.obrien.picesmart.utils.DTO.SearchDTO;
import com.obrien.picesmart.utils.DTO.UserSendingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SearchController {
    private int size = 10;

    @Autowired
    ProductService productService;

    @PostMapping(path = "/products/{page}")
    public String handleSearchPosts(@PathVariable int page, @ModelAttribute("search") SearchDTO search, Model model){

        List<ProductDTO> products =  productService.getSearchAndSortedProducts(search, size, page);
        model.addAttribute("products", products);
        model.addAttribute("search", search);
        return "product-search";
    }
}
