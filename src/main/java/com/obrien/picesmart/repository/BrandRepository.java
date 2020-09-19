package com.obrien.picesmart.repository;


import com.obrien.picesmart.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> findByEmail(String email);
    List<Brand> findByMobile(String mobile);
}
