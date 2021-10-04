package com.example.artbookhilttesting.di

import android.content.Context
import androidx.room.Room
import com.example.artbookhilttesting.api.EndPoint
import com.example.artbookhilttesting.constants.Const
import com.example.artbookhilttesting.room.ArtDao
import com.example.artbookhilttesting.room.ArtDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /* provide roomDB */
    @Singleton
    @Provides
    fun injectRoomDatabase( @ApplicationContext context: Context) = Room.databaseBuilder(
        context,ArtDatabase::class.java,Const.ROOM_DB_NAME
    ).build()

    @Singleton
    @Provides
    fun injectDao( database: ArtDatabase ) = database.artDao()



    /* retrofit provides */
    @Singleton
    @Provides
    fun injectEndPoint() : EndPoint{
       return Retrofit.Builder().baseUrl(Const.BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .build().create(EndPoint::class.java)
    }


}