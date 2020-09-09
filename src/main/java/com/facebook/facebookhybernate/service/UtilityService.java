package com.facebook.facebookhybernate.service;

import com.facebook.facebookhybernate.models.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UtilityService {
    public List<Post> setPostLikes(List<Post> posts,long user_id);
    public Post setPostLikes(Post post,long user_id);
}
