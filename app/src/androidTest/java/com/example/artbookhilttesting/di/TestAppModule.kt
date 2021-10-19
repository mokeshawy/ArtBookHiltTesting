package com.example.artbookhilttesting.di

import android.content.Context
import androidx.room.Room
import com.example.artbookhilttesting.room.ArtDatabase
import com.example.artbookhilttesting.ui.fragment.artsfragment.viewmodel.ArtsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("testDatabase")
    fun injectInMemoryRoom(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context,ArtDatabase::class.java)
            .allowMainThreadQueries()
            .build()

}