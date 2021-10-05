package com.example.artbookhilttesting.ui.fragment.artsfragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artbookhilttesting.constants.Resource
import com.example.artbookhilttesting.model.ArtModel
import com.example.artbookhilttesting.model.ImageResponse
import com.example.artbookhilttesting.ui.fragment.artsfragment.repository.ArtsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtsViewModel
@Inject
constructor( private val artsRepository: ArtsRepository): ViewModel() {


    /* get artList from repository */
    val artList = artsRepository.getArt()


    /* delete art */
    fun deleteArt(artModel: ArtModel) = viewModelScope.launch{
        artsRepository.deleteArt(artModel)
    }

}