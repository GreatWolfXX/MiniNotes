package com.artemnaumovdev.mininotes.data.db;

import com.artemnaumovdev.mininotes.data.dto.NoteDto;

import java.util.ArrayList;

public interface Dao {
    long insert(NoteDto data);

    ArrayList<NoteDto> getAll();
    NoteDto getById(long id);

    long updateById(NoteDto data);

    long deleteById(long id);
}
