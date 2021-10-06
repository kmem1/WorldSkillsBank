package com.example.worldskillsbank.di

import android.app.Application
import android.content.Context
import com.example.worldskillsbank.data.repository.RepositoryImpl
import com.example.worldskillsbank.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideRepository(context: Context): Repository {
        return RepositoryImpl(context)
    }
}