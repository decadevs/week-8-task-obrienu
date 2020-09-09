package com.facebook.facebookhybernate.repos;


import com.facebook.facebookhybernate.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface provides native JPA crude functionality by extending the
 * CrudRepository Interface.
 */
@Repository
public interface UserRepository  extends CrudRepository<User, Long> {
    List<User> findByEmail(String email);
    List<User> findByMobile(String mobile);
}
