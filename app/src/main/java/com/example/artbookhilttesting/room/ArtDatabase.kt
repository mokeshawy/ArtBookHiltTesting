package com.example.artbookhilttesting.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.artbookhilttesting.model.ArtModel
import javax.inject.Named

@Database( entities = [ArtModel::class] , version = 1)
abstract class ArtDatabase : RoomDatabase() {
    abstract fun artDao() : ArtDao
}