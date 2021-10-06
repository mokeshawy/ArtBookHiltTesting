package com.example.artbookhilttesting.repo

import androidx.lifecycle.MutableLiveData
import com.example.artbookhilttesting.constants.Resource
import com.example.artbookhilttesting.model.ArtModel
import com.example.artbookhilttesting.model.ImageResponse
import com.example.artbookhilttesting.ui.fragment.imageapifragment.repository.ImageApiRepositoryInterface

class FakeImageApiRepository : ImageApiRepositoryInterface{

    private val arts = mutableListOf<ArtModel>()
    private val artsLiveData = MutableLiveData<List<ArtModel>>(arts)
    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return Resource.success(ImageResponse(listOf(),0,0))
    }
}