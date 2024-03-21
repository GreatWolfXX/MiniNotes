package com.artemnaumovdev.mininotes.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.artemnaumovdev.mininotes.data.dto.NoteDto;
import com.artemnaumovdev.mininotes.data.repository.Repository;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

@HiltViewModel
public class MainViewModel extends ViewModel {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final Repository repository;

    private final MutableLiveData<ArrayList<NoteDto>> notesList = new MutableLiveData<>();

    @Inject
    public MainViewModel(Repository repository) {
        this.repository = repository;
    }

    public void addNote(NoteDto noteDto) {
        Disposable disposable = repository.insert(noteDto).observeOn(AndroidSchedulers.mainThread()).subscribe(data -> {},
                error -> {
                    Log.d("DBApp","DB insert error");
                }, this::getAllNotes);
        compositeDisposable.add(disposable);
    }

    public MutableLiveData<ArrayList<NoteDto>> getAllNotes() {
        Disposable disposable = repository.getAll().observeOn(AndroidSchedulers.mainThread()).subscribe(notesList::setValue,
                error -> {
                    Log.d("DBApp","DB get all error");
                });
        compositeDisposable.add(disposable);
        return notesList;
    }

    public void deleteNoteById(long id) {
        Disposable disposable = repository.deleteById(id).observeOn(AndroidSchedulers.mainThread()).subscribe(data -> {},
                error -> {
                    Log.d("DBApp","DB delete by id: " + id + " error");
                },
                this::getAllNotes
        );
        compositeDisposable.add(disposable);
    }

    public void editNoteById(NoteDto noteDto) {
        Disposable disposable = repository.updateById(noteDto).observeOn(AndroidSchedulers.mainThread()).subscribe(data -> {},
                error -> {
                    Log.d("DBApp","DB update by id: " + noteDto.getId() + " error");
                }, this::getAllNotes);
        compositeDisposable.add(disposable);
    }

    public void disposeAll() {
        compositeDisposable.dispose();
    }

}
