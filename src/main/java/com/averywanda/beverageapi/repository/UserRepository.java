package com.averywanda.beverageapi.repository;

import com.averywanda.beverageapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Use w/ register feature(s).
     * @param email
     * @return
     */
    boolean existsByEmail(String email);

    /**
     * Use w/ login feature(s).
     * @param email
     * @return
     */
    User findUserByEmail(String email);
}
