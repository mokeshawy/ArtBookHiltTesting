package com.example.artbookhilttesting.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.artbookhilttesting.model.ArtModel

@Dao
interface ArtDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArt( artModel: ArtModel)

    @Delete
    suspend fun deleteArt( artModel: ArtModel)

    @Query("SELECT * FROM arts")
    fun observeArts() : LiveData<List<ArtModel>>
}