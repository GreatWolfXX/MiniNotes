package com.artemnaumovdev.mininotes.data.db;

import android.provider.BaseColumns;

public final class NotesContract {

    private NotesContract() {}

    public static class NotesEntry implements BaseColumns {
        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";
    }

}