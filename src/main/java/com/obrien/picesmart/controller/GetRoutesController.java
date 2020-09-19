package com.obrien.picesmart.controller;


import com.obrien.picesmart.model.Product;
import com.obrien.picesmart.model.Review;
import com.obrien.picesmart.services.BrandService;
import com.obrien.picesmart.services.ProductService;
import com.obrien.picesmart.services.ReviewService;
import com.obrien.picesmart.utils.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class GetRoutesController {

    private int size = 10;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping(path = "/")
    public String getIndexPage(Model model, HttpSession httpSession){
        UserSendingDTO user = (UserSendingDTO) httpSession.getAttribute("user");
        List<ProductDTO> products =  productService.getProducts(0, size);
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping(path = "/auth")
    public String authPage(Model model, HttpSession httpSession){
        UserSendingDTO user = (UserSendingDTO) httpSession.getAttribute("user");
        BrandSendingDTO brand = (BrandSendingDTO) httpSession.getAttribute("brand");
        if(user != null || brand != null){
            return "redirect:/";
        }
        model.addAttribute("userSignIn", new SignInReceivingDTO());
        model.addAttribute("brandSignIn", new SignInReceivingDTO());
        model.addAttribute("userReceiverDTO", new UserReceivingDTO());
        model.addAttribute("brandReceiverDTO", new BrandReceivingDTO());
        return "auth";
    }


    @GetMapping(path="/brand/{brand_id}/{page}")
    public String getAdminPage(@PathVariable long brand_id, @PathVariable int page, Model model){
        BrandSendingDTO brand = brandService.getBrandSendingDTOById(brand_id);
        List<ProductDTO> products =  productService.getBrandProducts(brand_id, page - 1, size);

        model.addAttribute("brand", brand);
        model.addAttribute("products", products);
        model.addAttribute("editBrand", new BrandSendingDTO());
        model.addAttribute("editProduct", new ProductDTO());
        model.addAttribute("addProduct", new ProductDTO());
        return "brand";
    }

    @GetMapping(path="/product/add")
    public String getAddProductPage(Model model, HttpSession httpSession){
        BrandSendingDTO brand = (BrandSendingDTO) httpSession.getAttribute("brand");
        if(brand == null){
            return "redirect:/";
        }
        model.addAttribute("addProduct", new ProductDTO());
        return "add";
    }

    @GetMapping(path="/product/edit/{productId}")
    public String getEditProductPage(@PathVariable long productId, Model model, HttpSession httpSession){
        BrandSendingDTO brand = (BrandSendingDTO) httpSession.getAttribute("brand");
        if(brand == null){
            return "redirect:/";
        }
        Product product = productService.getProduct(productId);
        model.addAttribute("product", product);
        return "edit";
    }

    @GetMapping(path="/brand/edit/{brandId}")
    public String getAddBrandPage(@PathVariable long brandId, Model model, HttpSession httpSession){
        BrandSendingDTO brand = (BrandSendingDTO) httpSession.getAttribute("brand");
        if(brand == null){
            return "redirect:/";
        }
        if(brand.getId() != brandId){
            return "redirect:/";
        }
        BrandSendingDTO brandSendingDTO = brandService.getBrandSendingDTOById(brandId);
        model.addAttribute("brand", brandSendingDTO);
        return "edit-brand";
    }

    @GetMapping(path="/product/view/{productId}/{page}")
    public String getProductPage(@PathVariable long productId, @PathVariable int page, Model model){
        ProductDTO product = productService.getProductDTO(productId);
        long totalReviews = reviewService.getTotalProductReviews(productId);
        product.setTotalReviews((int)totalReviews);
        Iterable<Review> reviews = reviewService.getReviews(productId, page ,size);
        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);
        model.addAttribute("review", new ReviewDTO());
        return "view";
    }
}
