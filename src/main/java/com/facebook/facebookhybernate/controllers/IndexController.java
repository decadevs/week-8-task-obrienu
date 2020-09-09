package com.facebook.facebookhybernate.controllers;

import com.facebook.facebookhybernate.models.Post;
import com.facebook.facebookhybernate.models.User;
import com.facebook.facebookhybernate.models.UserSignIn;
import com.facebook.facebookhybernate.service.PostService;
import com.facebook.facebookhybernate.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The controller  is responsible for all get requests for pages
 * within the app.
 * All methods with the exception of the getAuthPage method
 * check for user in section and handles routing appropriately
 */
@Controller
public class IndexController {

    @Autowired
    PostService postService;

    @Autowired
    UtilityService utilityService;


    /**
     * Handles routing to the login page
     * it gets posts from the post service passes the posts to the
     * utility service to set likes and comments status based on the
     * user in session. it then passes the updated post to the view engine
     * to populate the HTML page.
     * @param model
     * @param httpSession
     * @return
     */
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

    /**
     * Method handles routing to individual posts. it gets the post from the post service
     * passes the post to the utility service to handle likes on post and comments the sends
     * the updated post to the view engine
     * @param post_id
     * @param model
     * @param httpSession
     * @return
     */
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

    /**
     * Handles routing to the login and sign up page
     * It also sends the user object to capture user sign up details on the
     * sign up form
     * and the userSignIn object to capture user login input as UserSignIn objects
     * @param model
     * @return
     */
    @GetMapping("/auth")
    public String getAuthPage(Model model){
        model.addAttribute("userSignIn", new UserSignIn());
        model.addAttribute("user", new User());
        return "auth";
    }

}
