package com.example.artbookhilttesting.ui.fragment.adddetailsfragment.repository

import androidx.lifecycle.LiveData
import com.example.artbookhilttesting.constants.Resource
import com.example.artbookhilttesting.model.ArtModel
import com.example.artbookhilttesting.model.ImageResponse

interface AddArtsDetailsRepositoryInterface {

    suspend fun insertArt( artModel: ArtModel)

    suspend fun deleteArt( artModel: ArtModel )

    fun getArt() : LiveData<List<ArtModel>>

    suspend fun searchImage( imageString : String ) : Resource<ImageResponse>
}