package com.example.artbookhilttesting.ui.fragment.imageapifragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artbookhilttesting.constants.Resource
import com.example.artbookhilttesting.model.ArtModel
import com.example.artbookhilttesting.model.ImageResponse
import com.example.artbookhilttesting.ui.fragment.imageapifragment.repository.ImageApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageApiViewModel
    @Inject
    constructor( private val imageApiRepository: ImageApiRepository) : ViewModel() {


    /* get image from imageResponse */
    val images = MutableLiveData<Resource<ImageResponse>>()


    /* delete art */
    fun deleteArt(artModel: ArtModel) = viewModelScope.launch{
        imageApiRepository.deleteArt(artModel)
    }

    /* insertArt */
    fun insertArt( artModel: ArtModel) = viewModelScope.launch {
        imageApiRepository.insertArt(artModel)
    }


    /* search for image */
    fun searchForImage( searchString : String ) {
        if(searchString.isEmpty()){
            return
        }
        images.value = Resource.loading(null)
        viewModelScope.launch {
            val response = imageApiRepository.searchImage(searchString)
            images.value = response
        }
    }
}