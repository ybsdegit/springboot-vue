package com.ybs.seckil.repository;

import com.ybs.seckil.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 *
 * @author Paulson
 * @date 2020/3/12 19:22
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public User findByUsernameAndPassword(String username, String password);
    public User findByUsername(String username);
}
