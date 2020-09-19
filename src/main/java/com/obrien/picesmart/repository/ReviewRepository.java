package com.obrien.picesmart.repository;

import com.obrien.picesmart.model.Brand;
import com.obrien.picesmart.model.Review;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Repository
public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {
    @Modifying
    @Query(value = "select * from review where product_id =:product_id " +
            "order by timestamp desc limit :offset, :limit", nativeQuery = true)
    @Transactional
    public List<Review> findAllReviews(@Param("product_id") long product_id,
                                       @Param("limit") int limit, @Param("offset") int offset );
    @Modifying
    @Query(value = "SELECT count(*) FROM review WHERE product_id = :productId", nativeQuery = true)
    public int count(@Param("productId") long productId);

    long countByProductId(long productId);
}
