package com.facebook.facebookhybernate.repos;

import com.facebook.facebookhybernate.models.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface provides native JPA crude functionality by extending the
 * CrudRepository Interface.
 */
@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

}
