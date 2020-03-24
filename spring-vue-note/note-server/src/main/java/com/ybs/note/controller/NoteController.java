package com.ybs.note.controller;

import com.ybs.note.pojo.Category;
import com.ybs.note.pojo.Note;
import com.ybs.note.response.Response;
import com.ybs.note.service.CategoryService;
import com.ybs.note.service.NoteService;
import com.ybs.note.vo.NoteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

/**
 * NoteController
 *
 * @author Paulson
 * @date 2020/3/24 1:03
 */

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private NoteService noteService;

    @GetMapping("/categories")
    public Response getCategories(){
        return Response.success(categoryService.getAll());
    }


    @GetMapping("/categories/{id}/notes")
    public Response getNotes(@PathVariable Integer id){
        return Response.success(noteService.getNotesByCategory(id));
    }

    @PostMapping("/update/note/{id}/info")
    public Response updateNote(@RequestBody NoteVo noteVo, @PathVariable Integer id){


        try {
            Note note = noteService.getById(id);
            System.out.println(note);
            note.setId(noteVo.getId());
            note.setName(noteVo.getName());
            note.setAbs(noteVo.getAbs());
            note.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
            noteService.updateNote(note);
            return Response.success();
        }catch (Exception e){
            return Response.fail(e.getMessage());
        }
    }

    @PostMapping("/add/note/info/{id}")
    public Response addNote(@RequestBody NoteVo addNote, @PathVariable Integer id){
        Category category = categoryService.getById(id);
        Note note = new Note();
        note.setName(addNote.getName());
        note.setAbs(addNote.getAbs());
        note.setCategory(category);
        note.setCreateTime(new Timestamp(System.currentTimeMillis()));
        note.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
        noteService.updateNote(note);
        return Response.success();
    }

    @GetMapping("/note/delete/{id}")
    public Response deleteNote(@PathVariable Integer id){
        noteService.deleteById(id);
        return Response.success();
    }
}
