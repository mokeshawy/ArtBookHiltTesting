package com.example.artbookhilttesting.ui.fragment.imageapifragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artbookhilttesting.constants.Resource
import com.example.artbookhilttesting.model.ImageResponse
import com.example.artbookhilttesting.ui.fragment.imageapifragment.repository.ImageApiRepository
import com.example.artbookhilttesting.ui.fragment.imageapifragment.repository.ImageApiRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageApiViewModel
    @Inject
    constructor( private val imageApiRepositoryInterface : ImageApiRepositoryInterface) : ViewModel() {


    /* get image from imageResponse */
    val images = MutableLiveData<Resource<ImageResponse>>()


    /* search for image */
    fun searchForImage( searchString : String ) {
        if(searchString.isEmpty()){
            return
        }
        images.value = Resource.loading(null)
        viewModelScope.launch {
            val response = imageApiRepositoryInterface.searchImage(searchString)
            images.value = response
        }
    }
}