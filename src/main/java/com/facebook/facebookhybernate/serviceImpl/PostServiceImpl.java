package com.facebook.facebookhybernate.serviceImpl;

import com.facebook.facebookhybernate.models.Post;
import com.facebook.facebookhybernate.models.PostLike;
import com.facebook.facebookhybernate.models.User;
import com.facebook.facebookhybernate.repos.PagingAndSortingSupport;
import com.facebook.facebookhybernate.repos.PostLikesRepository;
import com.facebook.facebookhybernate.repos.PostRepository;
import com.facebook.facebookhybernate.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    PagingAndSortingSupport pagingAndSortingSupport;

    @Autowired
    PostLikesRepository postLikesRepository;

    @Override
    public void savePost(Post post, User user){
        post.setUser(user);
        postRepository.save(post);
    }

    @Override
    public Post getPostById(long id){
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent())
            return post.get();
        return null;
    }

    @Override
    public List<Post> getPosts(int number) {
        Pageable pageable = PageRequest.of(number, 10, Sort.Direction.DESC, "timestamp");
        Page<Post> page = pagingAndSortingSupport.findAll(pageable);
        return page.getContent();
    }

    @Override
    public boolean editPost(long id, String text) {
        Post post = getPostById(id);
        if(post == null){
            return false;
        }
        post.setPost(text);
        postRepository.save(post);
        return true;
    }

    @Override
    public void deletePost(long id){
        postRepository.deleteById(id);
    }

    @Override
    public void likePost(long user_id, long post_id){
        postLikesRepository.savePostLike(post_id, user_id);
        return;
    }

    @Override
    public void unlikePost(long user_id, long post_id){
        postLikesRepository.deletePostLike(post_id, user_id);
        return;
    }

}
