package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.FileEntity;
import com.udacity.jwdnd.course1.cloudstorage.model.NotesEntity;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotesEntityMapper {

    @Insert("INSERT INTO NOTES  (noteTitle,noteDescription, userId ) VALUES(#{noteTitle},#{noteDescription},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insertNewNotes(NotesEntity newNote);

    @Select("SELECT * FROM NOTES WHERE notes.userid = #{userId}")
    List<NotesEntity> findNotesByUserId(@Param("userId") Integer userId);

    @Delete("Delete FROM NOTES  WHERE noteId = #{noteId}")
    int deleteUserNote(Integer noteId);

    @Update("UPDATE NOTES " + "SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription} " + "WHERE noteId = #{noteId}")
    int updateNote(NotesEntity neUpdate);

}
