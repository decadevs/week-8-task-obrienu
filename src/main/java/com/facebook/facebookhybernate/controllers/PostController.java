package com.facebook.facebookhybernate.controllers;

import com.facebook.facebookhybernate.models.Post;
import com.facebook.facebookhybernate.models.User;
import com.facebook.facebookhybernate.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Handles all post  post, update, and delete methods
 * All method checks if a user is in session before executing
 * any method call
 */
@Controller
public class PostController {

    @Autowired
    PostService postService;


    /**
     * Handles create post call, it creates a post object
     * from user input and passes the object to the Post Service
     * @param post
     * @param httpSession
     * @return
     */
    @PostMapping("/posts/post")
    public String savePost(@RequestParam String post, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if(user == null){
            return "redirect:/auth";
        }
        Post newPost = new Post();
        newPost.setPost(post);
        postService.savePost(newPost, user);
        return "redirect:/";
    }

    /**
     * Method handles all put post put requests
     * @param post_id
     * @param post
     * @param httpSession
     * @return
     */
    @PostMapping("/posts/edit/{post_id}")
    public String editPost(@PathVariable("post_id") Long post_id,  @RequestParam String post, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if(user == null){
            return "redirect:/auth";
        }
        postService.editPost(post_id, post);
        return "redirect:/";
    }

    /**
     * Method handles all post delete request
     * @param post_id
     * @param httpSession
     * @return
     */
    @PostMapping("/posts/delete/{post_id}")
    public String deletePost( @PathVariable("post_id") Long post_id, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if(user == null){
            return "redirect:/auth";
        }
        postService.deletePost(post_id);
        return "redirect:/";
    }

    /**
     * Method handles all post like request
     * @param post_id
     * @param httpSession
     * @return
     */
    @PostMapping("/posts/like/{post_id}")
    public String likePost( @PathVariable("post_id") Long post_id, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if(user == null){
            return "redirect:/auth";
        }
        postService.likePost(user.getId(), post_id);
        return "redirect:/";
    }

    /**
     * Method handles all post unlike requests
     * @param post_id
     * @param httpSession
     * @return
     */
    @PostMapping("/posts/unlike/{post_id}")
    public String unlikePost( @PathVariable("post_id") Long post_id, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if(user == null){
            return "redirect:/auth";
        }
        postService.unlikePost(user.getId(), post_id);
        return "redirect:/";
    }
}
