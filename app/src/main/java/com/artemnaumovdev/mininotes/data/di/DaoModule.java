package com.artemnaumovdev.mininotes.data.di;

import android.database.sqlite.SQLiteOpenHelper;

import com.artemnaumovdev.mininotes.data.db.Dao;
import com.artemnaumovdev.mininotes.data.db.DaoImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DaoModule {
    @Singleton
    @Provides
    public static Dao provideDao(SQLiteOpenHelper sqLiteOpenHelper) {
        return new DaoImpl(sqLiteOpenHelper);
    }
}
