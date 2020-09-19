package com.obrien.picesmart.repository;


import com.obrien.picesmart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByEmail(String email);
    List<User> findByMobile(String mobile);

}