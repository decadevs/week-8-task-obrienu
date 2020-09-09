package com.facebook.facebookhybernate.repos;


import com.facebook.facebookhybernate.models.PostLike;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  Interface provides native JPA crude functionality by extending the
 *  CrudRepository Interface. It also provides some custom crude query
 *
 */
@Repository
public interface PostLikesRepository extends CrudRepository<PostLike, Long> {
    @Modifying
    @Query(value = "select post_id from post_likes where user_id =:user_id", nativeQuery = true)
    @Transactional
    public List<Long> findUsersLikes(@Param("user_id") long user_id);

    @Modifying
    @Query(value = "insert into post_likes (post_id ,user_id) VALUES (:post_id,:user_id)", nativeQuery = true)
    @Transactional
    public void savePostLike(@Param("post_id") long post_id, @Param("user_id") long user_id);

    @Modifying
    @Query(value = "DELETE FROM post_likes WHERE post_id=:post_id AND user_id=:user_id", nativeQuery = true)
    @Transactional
    public void deletePostLike(@Param("post_id") long post_id, @Param("user_id") long user_id);
}
