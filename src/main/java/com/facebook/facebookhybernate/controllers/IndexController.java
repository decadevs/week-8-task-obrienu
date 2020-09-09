package com.facebook.facebookhybernate.controllers;

import com.facebook.facebookhybernate.models.Post;
import com.facebook.facebookhybernate.models.User;
import com.facebook.facebookhybernate.models.UserSignIn;
import com.facebook.facebookhybernate.repos.PostLikesRepository;
import com.facebook.facebookhybernate.service.PostService;
import com.facebook.facebookhybernate.service.UserService;
import com.facebook.facebookhybernate.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Controller
public class IndexController {


    @Autowired
    PostService postService;

    @Autowired
    UtilityService utilityService;


    @GetMapping("/")
    public String getIndexPage(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if(user == null){
            return "redirect:/auth";
        }
        List<Post> posts = postService.getPosts(0);
        List<Post> updatedPosts = utilityService.setPostLikes(posts, user.getId());
        model.addAttribute("posts", updatedPosts);
        model.addAttribute("user", user);
        model.addAttribute("newPost", new Post());
        return "index";
    }

    @GetMapping("/post/{post_id}")
    public String getCommentPage(@PathVariable("post_id") Long post_id, Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if(user == null){
            return "redirect:/auth";
        }
        Post post = postService.getPostById(post_id);
        Post updatedPost = utilityService.setPostLikes(post, user.getId());

        model.addAttribute("post", updatedPost);
        return "post";
    }

    @GetMapping("/auth")
    public String getAuthPage(Model model){
        model.addAttribute("userSignIn", new UserSignIn());
        model.addAttribute("user", new User());
        return "auth";
    }

}
