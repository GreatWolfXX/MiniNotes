package com.artemnaumovdev.mininotes.data.repository;

import com.artemnaumovdev.mininotes.data.dto.NoteDto;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface Repository {
    Observable<Void> insert(NoteDto data);

    Single<ArrayList<NoteDto>> getAll();
    Single<NoteDto> getById(long id);

    Observable<Void> updateById(NoteDto data);

    Observable<Void> deleteById(long id);
}
