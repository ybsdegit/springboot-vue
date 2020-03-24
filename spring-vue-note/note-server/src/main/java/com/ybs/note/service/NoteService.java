package com.ybs.note.service;

import com.ybs.note.mapper.CategoryMapper;
import com.ybs.note.mapper.NoteMapper;
import com.ybs.note.pojo.Category;
import com.ybs.note.pojo.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CategoryService
 *
 * @author Paulson
 * @date 2020/3/24 1:01
 */

@Service
public class NoteService {

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Note> getNotesByCategory(Integer id){
        Category category = categoryMapper.getOne(id);
        return noteMapper.findAllByCategory(category);
    }

    public Note getById(Integer id){
        return noteMapper.findById(id).get();
    }

    public void deleteById(Integer id){
        noteMapper.deleteById(id);
    }

    public void updateNote(Note note){
       noteMapper.save(note);
    }


}
