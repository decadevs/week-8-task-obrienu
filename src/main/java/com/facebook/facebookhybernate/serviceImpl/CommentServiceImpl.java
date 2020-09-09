package com.facebook.facebookhybernate.serviceImpl;


import com.facebook.facebookhybernate.models.Comment;
import com.facebook.facebookhybernate.models.Post;
import com.facebook.facebookhybernate.repos.CommentLikesRepository;
import com.facebook.facebookhybernate.repos.CommentRepository;
import com.facebook.facebookhybernate.service.CommentService;
import com.facebook.facebookhybernate.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentLikesRepository commentLikesRepository;

    @Override
    public void createComment(Comment comment, long post_id){
        Post post = new Post();
        post.setId(post_id);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Override
    public Comment getComment(long comment_id){
      Optional<Comment> comment = commentRepository.findById(comment_id);
       if(comment.isEmpty()){
           return null;
       }
       return comment.get();
    }

    @Override
    public void editComment(String text, long comment_id){
        Comment comment = getComment(comment_id);
        comment.setComment(text);
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(long comment_id){
        commentRepository.deleteById(comment_id);
    }

    @Override
    public void likeComment(long user_id, long comment_id){
        commentLikesRepository.saveCommentLike(comment_id, user_id);
    }

    @Override
    public void unlikeComment(long user_id, long comment_id){
        commentLikesRepository.deleteCommentLike(comment_id, user_id);
    }


}
