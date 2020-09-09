package com.facebook.facebookhybernate.serviceImpl;

import com.facebook.facebookhybernate.models.Comment;
import com.facebook.facebookhybernate.models.Post;
import com.facebook.facebookhybernate.repos.CommentLikesRepository;
import com.facebook.facebookhybernate.repos.PostLikesRepository;
import com.facebook.facebookhybernate.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilityServiceImpl implements UtilityService {


    @Autowired
    PostLikesRepository postLikesRepository;

    @Autowired
    CommentLikesRepository commentLikesRepository;

    @Override
    public List<Post> setPostLikes(List<Post> posts,long user_id){
        List<Long> likes = postLikesRepository.findUsersLikes(user_id);
        for(Post post: posts){
            post.setLiked(likes.contains(post.getId()));
        }
       return posts;
    }

    @Override
    public Post setPostLikes(Post post,long user_id){
        List<Long> likes = postLikesRepository.findUsersLikes(user_id);
        List<Long> commentsLike = commentLikesRepository.findUsersCommentsLikes(user_id);
        post.setLiked(likes.contains(post.getId()));
        for(Comment comment : post.getComments()){
            comment.setLiked(commentsLike.contains(comment.getId()));
        }
        return post;
    }


}
