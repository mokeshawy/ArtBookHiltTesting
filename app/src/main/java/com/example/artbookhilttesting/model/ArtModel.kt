package com.example.artbookhilttesting.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "arts")
data class ArtModel(
    var name        : String ,
    var artisName   : String,
    var year        : Int,
    var imageUrl    : String,
    @PrimaryKey(autoGenerate = true)
    var id          : Int? = null
)