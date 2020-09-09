package com.facebook.facebookhybernate.controllers;

import com.facebook.facebookhybernate.models.Post;
import com.facebook.facebookhybernate.models.User;
import com.facebook.facebookhybernate.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class PostController {

    @Autowired
    PostService postService;


    @PostMapping("/posts/post")
    public String savePost(@RequestParam String post, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        Post newPost = new Post();
        newPost.setPost(post);
        postService.savePost(newPost, user);
        return "redirect:/";
    }

    @PostMapping("/posts/edit/{post_id}")
    public String editPost(@PathVariable("post_id") Long post_id,  @RequestParam String post){
        postService.editPost(post_id, post);
        return "redirect:/";
    }

    @PostMapping("/posts/delete/{post_id}")
    public String deletePost( @PathVariable("post_id") Long post_id){
        postService.deletePost(post_id);
        return "redirect:/";
    }

    @PostMapping("/posts/like/{post_id}")
    public String likePost( @PathVariable("post_id") Long post_id, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        postService.likePost(user.getId(), post_id);
        return "redirect:/";
    }

    @PostMapping("/posts/unlike/{post_id}")
    public String unlikePost( @PathVariable("post_id") Long post_id, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        postService.unlikePost(user.getId(), post_id);
        return "redirect:/";
    }
}
