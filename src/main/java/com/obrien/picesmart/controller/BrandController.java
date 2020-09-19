package com.obrien.picesmart.controller;

import com.obrien.picesmart.services.BrandService;
import com.obrien.picesmart.services.ProductService;
import com.obrien.picesmart.utils.DTO.BrandReceivingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class BrandController {
    @Autowired
    BrandService brandService;
    @Autowired
    ProductService productService;


    @PostMapping(path="brand/edit/{brandId}")
    public String editBrand(@ModelAttribute("userSignIn") BrandReceivingDTO editBrand, @PathVariable long brandId, HttpSession session){
        brandService.updateBrand(editBrand, brandId);
        return "redirect:/brand/" + brandId+ "/" + 1;
    }

}
