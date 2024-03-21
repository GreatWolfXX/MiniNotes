package com.artemnaumovdev.mininotes.data.repository;

import com.artemnaumovdev.mininotes.data.db.Dao;
import com.artemnaumovdev.mininotes.data.dto.NoteDto;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RepositoryImpl implements Repository {

    private final Dao dao;

    @Inject
    public RepositoryImpl(Dao dao) {
        this.dao = dao;
    }

    @Override
    public Observable<Void> insert(NoteDto data) {
        return Completable.create(source -> {
            dao.insert(data);
            source.onComplete();
        }).subscribeOn(Schedulers.io()).andThen(Observable.empty());
    }

    @Override
    public Single<ArrayList<NoteDto>> getAll() {
        return Single.create((SingleEmitter<ArrayList<NoteDto>> source) -> {
            source.onSuccess(dao.getAll());
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<NoteDto> getById(long id) {
        return Single.create((SingleEmitter<NoteDto> source) -> {
            source.onSuccess(dao.getById(id));
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Void> updateById(NoteDto data) {
        return Completable.create(source -> {
            dao.updateById(data);
            source.onComplete();
        }).subscribeOn(Schedulers.io()).andThen(Observable.empty());
    }

    @Override
    public Observable<Void> deleteById(long id) {
        return Completable.create(source -> {
            dao.deleteById(id);
            source.onComplete();
        }).subscribeOn(Schedulers.io()).andThen(Observable.empty());
    }
}
