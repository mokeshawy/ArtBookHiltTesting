package com.example.artbookhilttesting.ui.fragment.artsfragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artbookhilttesting.model.ArtModel
import com.example.artbookhilttesting.ui.fragment.artsfragment.repository.ArtsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtsViewModel
@Inject
constructor( private val artsRepository: com.example.artbookhilttesting.repo.FakeArtsRepository): ViewModel() {


    /* get artList from repository */
    val artList = artsRepository.getArt()


    /* delete art */
    fun deleteArt(artModel: ArtModel) = viewModelScope.launch{
        artsRepository.deleteArt(artModel)
    }

}