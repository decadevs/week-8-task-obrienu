package com.facebook.facebookhybernate.serviceImpl;

import com.facebook.facebookhybernate.models.Post;
import com.facebook.facebookhybernate.models.User;
import com.facebook.facebookhybernate.repos.PostLikesRepository;
import com.facebook.facebookhybernate.service.PostService;
import com.facebook.facebookhybernate.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PostServiceImplTest {

    @Autowired
    UserService userService;
    @Autowired
    PostService postService;

    @Autowired
    PostLikesRepository postLikesRepository;

    @Test
    void savePost() {
        User user = userService.getUserById(4L);
        Post post = new Post();
        post.setPost("Hello njnj vjnvjf fvvnfn sndn nnvv nvdnvn vjnjnv jsjdnc ndc jdnjn nvjnvjs nscjncjnj sdjcjdcnjnd  dj dc bbv  snjnjnj djnjnjsnd cdhhb  dc jnjsnjndc  cjjsnjs a thousand times");
        postService.savePost(post, user);
    }

    @Test
    void getPost(){
        Post post = postService.getPostById(3L);
        System.out.println(post.getComments().size());
    }

    @Test
    void getPosts(){
        List<Post> posts = postService.getPosts(0);
        assertEquals(2, posts.size());

    }

    @Test
    void editPosts(){
        postService.editPost(3L, "hello there");
        Post post = postService.getPostById(3L);
        assertEquals("hello there", post.getPost());
    }

    @Test
    void likePost(){
        postService.likePost(1L, 2L);
    }

    @Test
    void getUserLikes() {
        List<Long> list = postLikesRepository.findUsersLikes(1L);
        list.forEach(System.out::println);
    }
}