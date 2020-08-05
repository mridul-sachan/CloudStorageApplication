package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesEntityMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.NotesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateNotesService {

    @Autowired
    private NotesEntityMapper notesEntityMapper;

    public int createNewNote(NotesEntity ne)
    {
       return notesEntityMapper.insertNewNotes(ne);
    }
}
