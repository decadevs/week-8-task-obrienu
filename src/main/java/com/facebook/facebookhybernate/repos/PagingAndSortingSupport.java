package com.facebook.facebookhybernate.repos;

import com.facebook.facebookhybernate.models.Post;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface provides native JPA crude functionality by extending the
 * CrudRepository Interface.
 */
@Repository
public interface PagingAndSortingSupport extends PagingAndSortingRepository<Post, Long> {

}
