package com.artemnaumovdev.mininotes.common;

import com.artemnaumovdev.mininotes.data.db.NotesContract.*;

public final class Constants {

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NotesEntry.TABLE_NAME + " (" +
                    NotesEntry._ID + " INTEGER PRIMARY KEY," +
                    NotesEntry.COLUMN_TITLE + " TEXT," +
                    NotesEntry.COLUMN_DESCRIPTION + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NotesEntry.TABLE_NAME;

}
