package com.artemnaumovdev.mininotes.data.di;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.artemnaumovdev.mininotes.data.db.DbHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {
    @Singleton
    @Provides
    public static SQLiteOpenHelper provideDbHelper(@ApplicationContext Context context) {
        return new DbHelper(context);
    }
}
