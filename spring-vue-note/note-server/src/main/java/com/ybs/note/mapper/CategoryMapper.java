package com.ybs.note.mapper;

import com.ybs.note.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CategoryMapper
 *
 * @author Paulson
 * @date 2020/3/24 1:00
 */

public interface CategoryMapper  extends JpaRepository<Category, Integer> {
}
