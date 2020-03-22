package com.ybs.note.mapper;

import com.ybs.note.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserMapper
 *
 * @author Paulson
 * @date 2020/3/22 20:31
 */

public interface UserMapper extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
