package com.facebook.facebookhybernate.repos;

import com.facebook.facebookhybernate.models.CommentLike;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Interface provides native JPA crude functionality by extending the
 * CrudRepository Interface. It also provides some custom crude query
 */
@Repository
public interface CommentLikesRepository  extends CrudRepository<CommentLike, Long> {
    @Modifying
    @Query(value = "select comment_id from comment_likes where user_id =:user_id", nativeQuery = true)
    @Transactional
    public List<Long> findUsersCommentsLikes(@Param("user_id") long user_id);

    @Modifying
    @Query(value = "insert into comment_likes (comment_id ,user_id) VALUES (:comment_id,:user_id)", nativeQuery = true)
    @Transactional
    public void saveCommentLike(@Param("comment_id") long comment_id, @Param("user_id") long user_id);

    @Modifying
    @Query(value = "DELETE FROM comment_likes WHERE comment_id=:comment_id AND user_id=:user_id", nativeQuery = true)
    @Transactional
    public void deleteCommentLike(@Param("comment_id") long comment_id, @Param("user_id") long user_id);
}
