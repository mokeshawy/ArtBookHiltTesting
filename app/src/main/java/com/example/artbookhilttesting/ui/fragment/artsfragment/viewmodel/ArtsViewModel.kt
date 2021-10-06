package com.example.artbookhilttesting.ui.fragment.artsfragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artbookhilttesting.model.ArtModel
import com.example.artbookhilttesting.ui.fragment.artsfragment.repository.ArtsRepository
import com.example.artbookhilttesting.ui.fragment.artsfragment.repository.ArtsRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtsViewModel
@Inject
constructor( private val artsRepositoryInterface : ArtsRepositoryInterface ): ViewModel() {


    /* get artList from repository */
    val artList = artsRepositoryInterface.getArt()


    /* delete art */
    fun deleteArt(artModel: ArtModel) = viewModelScope.launch{
        artsRepositoryInterface.deleteArt(artModel)
    }

}