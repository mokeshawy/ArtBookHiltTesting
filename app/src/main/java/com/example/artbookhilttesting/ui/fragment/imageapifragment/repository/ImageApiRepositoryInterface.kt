package com.example.artbookhilttesting.ui.fragment.imageapifragment.repository

import com.example.artbookhilttesting.constants.Resource
import com.example.artbookhilttesting.model.ImageResponse

interface ImageApiRepositoryInterface {

    suspend fun searchImage( imageString : String ) : Resource<ImageResponse>
}