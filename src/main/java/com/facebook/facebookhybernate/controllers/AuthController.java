package com.facebook.facebookhybernate.controllers;

import com.facebook.facebookhybernate.models.User;
import com.facebook.facebookhybernate.models.UserSignIn;
import com.facebook.facebookhybernate.service.UserService;
import com.facebook.facebookhybernate.utils.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public String loginUsers(@ModelAttribute("userSignIn") UserSignIn userSignIn, RedirectAttributes redirectAttrs,
                             HttpSession httpSession){
        ServiceResponse response = userService.userLogin(userSignIn);
        if(!response.isStatus()){
            redirectAttrs.addFlashAttribute("response", response);
            return "redirect:/auth";
        }
        httpSession.setAttribute("user", (User) response.getData());
        return "redirect:/";
    }

    @PostMapping("/register")
    public String registerUsers(@ModelAttribute(name = "user") User user, RedirectAttributes redirectAttrs){
        ServiceResponse response = userService.userRegistration(user);
        if(!response.isStatus()){
            redirectAttrs.addFlashAttribute("response", response);
            return "redirect:/auth";
        }
        return "redirect:/auth";
    }
}
