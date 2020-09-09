package com.facebook.facebookhybernate.repos;

import com.facebook.facebookhybernate.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface provides native JPA crude functionality by extending the
 * CrudRepository Interface.
 */
@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

}
