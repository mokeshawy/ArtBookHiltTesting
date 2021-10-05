package com.example.artbookhilttesting.ui.fragment.artsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artbookhilttesting.constants.Resource
import com.example.artbookhilttesting.model.ArtModel
import com.example.artbookhilttesting.model.ImageResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtsViewModel
@Inject
constructor( private val artRepository: ArtRepository): ViewModel() {

    /* get artList from repository */
    val artList = artRepository.getArt()

    /* get image from imageResponse */
    val images = MutableLiveData<Resource<ImageResponse>>()

    /* selected image as live data */
    val selectedImage = MutableLiveData<String>()

    /* insert art message as live data*/
    var insertArtMsg = MutableLiveData<Resource<ArtModel>>()


    /* delete art */
    fun deleteArt(artModel: ArtModel) = viewModelScope.launch{
        artRepository.deleteArt(artModel)
    }
}