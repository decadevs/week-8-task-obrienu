package com.facebook.facebookhybernate.controllers;


import com.facebook.facebookhybernate.models.Comment;
import com.facebook.facebookhybernate.models.User;
import com.facebook.facebookhybernate.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/comment/add/{post_id}")
    public String addNewComment(@PathVariable("post_id") Long post_id, @RequestParam String comment, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if(user == null){
            return "redirect:/auth";
        }
        Comment newComment = new Comment();
        newComment.setComment(comment);
        newComment.setUser(user);

        commentService.createComment(newComment, post_id);
        return "redirect:/";
    }

    @PostMapping("/comment/edit/{comment_id}")
    public String editComment(@PathVariable("comment_id") Long comment_id, @RequestParam String comment, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if(user == null){
            return "redirect:/auth";
        }
        commentService.editComment(comment, comment_id);
        return "redirect:/";
    }

    @PostMapping("/comment/delete/{comment_id}")
    public String deleteComment(@PathVariable("comment_id") Long comment_id, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if(user == null){
            return "redirect:/auth";
        }
        commentService.deleteComment(comment_id);
        return "redirect:/";
    }


    @PostMapping("/comment/like/{comment_id}")
    public String likeComment(@PathVariable("comment_id") Long comment_id, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if(user == null){
            return "redirect:/auth";
        }
        commentService.likeComment(user.getId(), comment_id);
        return "redirect:/";
    }

    @PostMapping("/comment/unlike/{comment_id}")
    public String unlikeComment(@PathVariable("comment_id") Long comment_id, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if(user == null){
            return "redirect:/auth";
        }
        commentService.unlikeComment(user.getId(), comment_id);
        return "redirect:/";
    }
}
