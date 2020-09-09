package com.facebook.facebookhybernate.service;

import com.facebook.facebookhybernate.models.Comment;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    public void createComment(Comment comment,long post_id);
    public void editComment(String text, long comment_id);
    public Comment getComment(long comment_id);
    public void deleteComment(long comment_id);
    public void likeComment(long user_id, long comment_id);
    public void unlikeComment(long user_id, long comment_id);
}
