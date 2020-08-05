package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesEntityMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.FileEntity;
import com.udacity.jwdnd.course1.cloudstorage.model.NotesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateNotesService {

    private final NotesEntityMapper notesEntityMapper;

    public CreateNotesService(NotesEntityMapper notesEntityMapper) { this.notesEntityMapper = notesEntityMapper; }

    public int createNewNote(NotesEntity ne) { return notesEntityMapper.insertNewNotes(ne); }

    public  List<NotesEntity> getAllNotes(Integer userId){
        return notesEntityMapper.findNotesByUserId(userId);
    }

    public int deleteNote(Integer noteId) { return notesEntityMapper.deleteUserNote(noteId); }
}
