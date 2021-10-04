package com.example.artbookhilttesting.ui.fragment.imageapifragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.artbookhilttesting.constants.Resource
import com.example.artbookhilttesting.model.ArtModel
import com.example.artbookhilttesting.model.ImageResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageApiViewModel
@Inject
constructor( private val imageApiRepository: ImageApiRepository): ViewModel(){

    /* get artList from repository */
    val artList = imageApiRepository.getArt()

    /* get image from imageResponse */
    val images = MutableLiveData<Resource<ImageResponse>>()

    /* selected image as live data */
    val selectedImage = MutableLiveData<String>()

    /* insert art message as live data*/
    var insertArtMsg = MutableLiveData<Resource<ArtModel>>()

}