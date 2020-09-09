package com.facebook.facebookhybernate.service;

import com.facebook.facebookhybernate.models.Post;
import com.facebook.facebookhybernate.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    public void savePost(Post post, User user);
    public boolean editPost(long id, String text);
    public Post getPostById(long id);
    public List<Post> getPosts(int number);
    public void deletePost(long id);
    public void likePost(long user_id, long post_id);
    public void unlikePost(long user_id, long post_id);
}
