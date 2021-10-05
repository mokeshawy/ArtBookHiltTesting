package com.example.artbookhilttesting.ui.fragment.artsfragment.repository

import androidx.lifecycle.LiveData
import com.example.artbookhilttesting.constants.Resource
import com.example.artbookhilttesting.model.ArtModel
import com.example.artbookhilttesting.model.ImageResponse

interface ArtsRepositoryInterface {

    suspend fun deleteArt( artModel: ArtModel )

    fun getArt() : LiveData<List<ArtModel>>

}