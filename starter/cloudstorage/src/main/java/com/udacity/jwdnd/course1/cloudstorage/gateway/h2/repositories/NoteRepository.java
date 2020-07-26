package com.udacity.jwdnd.course1.cloudstorage.gateway.h2.repositories;

import com.udacity.jwdnd.course1.cloudstorage.domain.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NoteRepository {

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Note> findNotesByUserId(int userId);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid)" +
            "VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insertNote(Note note);
}
