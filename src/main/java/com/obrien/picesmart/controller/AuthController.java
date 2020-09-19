package com.obrien.picesmart.controller;

import com.obrien.picesmart.services.BrandService;
import com.obrien.picesmart.services.UserService;
import com.obrien.picesmart.utils.DTO.*;
import com.obrien.picesmart.utils.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    BrandService brandService;

    @PostMapping(path = "/register/user")
    public String registerUser(@ModelAttribute("userReceiverDTO") UserReceivingDTO userReceiverDTO, RedirectAttributes redirectAttributes){
        ServiceResponse signUpResponse = userService.registerUser(userReceiverDTO);
        redirectAttributes.addFlashAttribute("signUpResponse", signUpResponse);
        return "redirect:/auth";
    }

    @PostMapping(path = "/register/brand")
    public String registerBrand(@ModelAttribute("brandReceiverDTO") BrandReceivingDTO brandReceiverDTO, RedirectAttributes redirectAttributes){
        ServiceResponse signUpResponse = brandService.registerBrand(brandReceiverDTO);
        redirectAttributes.addFlashAttribute("signUpResponse", signUpResponse);
        return "redirect:/auth";
    }

    @PostMapping(path = "/login/user")
    public String loginUser(@ModelAttribute("userSignIn") SignInReceivingDTO userSignIn, RedirectAttributes redirectAttributes,
                            HttpSession session){
        ServiceResponse signInResponse = userService.userLogin(userSignIn);
        if(signInResponse.isStatus()){
            UserSendingDTO user = (UserSendingDTO) signInResponse.getData();
            session.setAttribute("user", user);
            return "redirect:/";
        }else{
            redirectAttributes.addFlashAttribute("signInResponse", signInResponse);
            return "redirect:/auth";
        }

    }

    @PostMapping(path = "/login/brand")
    public String loginBrand(@ModelAttribute("brandSignIn") SignInReceivingDTO brandSignIn, RedirectAttributes redirectAttributes,
                            HttpSession session){
        ServiceResponse signInResponse = brandService.brandLogin(brandSignIn);
        if(signInResponse.isStatus()){
            BrandSendingDTO brand = (BrandSendingDTO) signInResponse.getData();
            session.setAttribute("brand", brand);
            return "redirect:/";
        }else{
            redirectAttributes.addFlashAttribute("signInResponse", signInResponse);
            return "redirect:/auth";
        }

    }

    @GetMapping(path = "/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}
