package com.artemnaumovdev.mininotes.data.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.artemnaumovdev.mininotes.data.db.NotesContract.NotesEntry;
import com.artemnaumovdev.mininotes.data.dto.NoteDto;

import java.util.ArrayList;

import javax.inject.Inject;

public class DaoImpl implements Dao {
    private final SQLiteOpenHelper sqLiteOpenHelper;

    @Inject
    public DaoImpl(SQLiteOpenHelper sqLiteOpenHelper){
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    @Override
    public long insert(NoteDto data) {
        long response = 0;
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NotesEntry.COLUMN_TITLE, data.getTitle());
        contentValues.put(NotesEntry.COLUMN_DESCRIPTION, data.getDescription());

        response = db.insert(NotesEntry.TABLE_NAME, NotesEntry.COLUMN_TITLE, contentValues);

        return response;
    }

    @Override
    public ArrayList<NoteDto> getAll() {
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        ArrayList<NoteDto> noteDtoList = new ArrayList<>();
        NoteDto noteDto;

        String[] projection = {NotesEntry._ID, NotesEntry.COLUMN_TITLE, NotesEntry.COLUMN_DESCRIPTION};

        Cursor cursor = db.query(NotesEntry.TABLE_NAME, projection, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            noteDto = new NoteDto();
            int titleId = cursor.getColumnIndex(NotesEntry._ID);
            int titleIndex = cursor.getColumnIndex(NotesEntry.COLUMN_TITLE);
            int descIndex = cursor.getColumnIndex(NotesEntry.COLUMN_DESCRIPTION);

            noteDto.setId(cursor.getLong(titleId));
            noteDto.setTitle(cursor.getString(titleIndex));
            noteDto.setDescription(cursor.getString(descIndex));

            noteDtoList.add(noteDto);
        }
        cursor.close();

        return noteDtoList;
    }

    @Override
    public NoteDto getById(long id) {
        NoteDto noteDto = new NoteDto();
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();

        String[] projection = {NotesEntry._ID, NotesEntry.COLUMN_TITLE, NotesEntry.COLUMN_DESCRIPTION};
        String selection = NotesEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};
        Cursor cursor = db.query(NotesEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        while (cursor.moveToNext()) {
            int titleId = cursor.getColumnIndex(NotesEntry._ID);
            int titleIndex = cursor.getColumnIndex(NotesEntry.COLUMN_TITLE);
            int descIndex = cursor.getColumnIndex(NotesEntry.COLUMN_DESCRIPTION);

            noteDto.setId(cursor.getLong(titleId));
            noteDto.setTitle(cursor.getString(titleIndex));
            noteDto.setDescription(cursor.getString(descIndex));
        }
        cursor.close();

        return noteDto;
    }

    @Override
    public long updateById(NoteDto data) {
        long response = 0;
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NotesEntry.COLUMN_TITLE, data.getTitle());
        contentValues.put(NotesEntry.COLUMN_DESCRIPTION, data.getDescription());

        String selection = NotesEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(data.getId())};
        response = db.update(NotesEntry.TABLE_NAME, contentValues, selection, selectionArgs);

        return response;
    }

    @Override
    public long deleteById(long id) {
        long response = 0;
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        String selection = NotesEntry._ID + " = ?";
        String[] selectionArgs = {Long.toString(id)};
        response = db.delete(NotesEntry.TABLE_NAME, selection, selectionArgs);

        return response;
    }
}
