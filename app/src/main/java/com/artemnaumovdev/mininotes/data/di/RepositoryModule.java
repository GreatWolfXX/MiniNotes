package com.artemnaumovdev.mininotes.data.di;

import com.artemnaumovdev.mininotes.data.db.Dao;
import com.artemnaumovdev.mininotes.data.repository.Repository;
import com.artemnaumovdev.mininotes.data.repository.RepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {
    @Singleton
    @Provides
    public static Repository provideRepository(Dao dao) {
        return new RepositoryImpl(dao);
    }
}
