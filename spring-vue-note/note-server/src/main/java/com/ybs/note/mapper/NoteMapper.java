package com.ybs.note.mapper;

import com.ybs.note.pojo.Category;
import com.ybs.note.pojo.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * CategoryMapper
 *
 * @author Paulson
 * @date 2020/3/24 1:00
 */

public interface NoteMapper extends JpaRepository<Note, Integer> {

    List<Note> findAllByCategory(Category category);


}
