package com.obrien.picesmart.repository;

import com.obrien.picesmart.model.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 *
 */
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    @Modifying
    @Query(value = "select * from products where brand_id =:brandId " +
            "order by timestamp desc limit :offset, :limit", nativeQuery = true)
    @Transactional
    public List<Product> findProductsByBrandId(@Param("brandId") long brandId,
                                       @Param("limit") int limit, @Param("offset") int offset );

    @Modifying
    @Query(value = "SELECT products.id AS id, products.description AS description,  " +
            "products.image_url AS imageUrl,  products.price AS price,   " +
            "products.category AS category, products.catch_phrase AS catchPhrase,  " +
            " IFNULL(CAST(AVG(review.rating) as DECIMAL(10, 1)), 0.0)  " +
            "AS averageRating, count(review.id) AS totalReviews,  brands.id AS brandId," +
            " brands.brand_name AS brandName, products.name AS name    FROM products LEFT JOIN review " +
            "ON products.id = review.product_id  INNER JOIN brands ON  " +
            "products.brand_id = brands.id WHERE brands.id =:brandId  GROUP BY products.id  " +
            "ORDER BY products.timestamp DESC LIMIT :offset, :limit ;", nativeQuery = true)
    @Transactional
    public List<Object[]> findProductsByBrand(@Param("brandId") long brandId, @Param("limit") int limit, @Param("offset") int offset );

    @Modifying
    @Query(value = "SELECT products.id AS id, products.description AS description,  " +
            "            products.image_url AS imageUrl,  products.price AS price,  " +
            "            products.category AS category, products.catch_phrase AS catchPhrase, " +
            "            IFNULL(CAST(AVG(review.rating) as DECIMAL(10, 1)), 0.0)  " +
            "            AS averageRating, count(review.id) AS totalReviews,  brands.id AS brandId, " +
            "             brands.brand_name AS brandName, products.name AS name    FROM products LEFT JOIN review " +
            "            ON products.id = review.product_id  INNER JOIN brands ON  " +
            "            products.brand_id = brands.id  GROUP BY products.id  " +
            "            ORDER BY products.timestamp DESC LIMIT :offset, :limit ;", nativeQuery = true)
    @Transactional
    public List<Object[]> findProducts(@Param("limit") int limit, @Param("offset") int offset );


    @Modifying
    @Query(value = "SELECT products.id AS id, products.description AS description,  " +
            "            products.image_url AS imageUrl,  products.price AS price,  " +
            "            products.category AS category, products.catch_phrase AS catchPhrase,  " +
            "            IFNULL(CAST(AVG(review.rating) as DECIMAL(10, 1)), 0.0)   " +
            "            AS averageRating, count(review.id) AS totalReviews,  brands.id AS brandId, " +
            "            brands.brand_name AS brandName, products.name AS name   FROM products LEFT JOIN review " +
            "            ON products.id = review.product_id  INNER JOIN brands ON  " +
            "            products.brand_id = brands.id WHERE products.id =:productId " +
            "            ", nativeQuery = true)
    @Transactional
    public List<Object[]> findProduct(@Param("productId") long productId);
}
