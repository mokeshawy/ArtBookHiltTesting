package com.example.artbookhilttesting.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.artbookhilttesting.model.ArtModel
import com.example.artbookhilttesting.ui.fragment.artsfragment.repository.ArtsRepositoryInterface

class FakeArtsRepositoryAndroid : ArtsRepositoryInterface{

    private val arts = mutableListOf<ArtModel>()
    private val artsLiveData = MutableLiveData<List<ArtModel>>(arts)

    override suspend fun deleteArt(artModel: ArtModel) {
        arts.remove(artModel)
        refreshData()
    }

    override fun getArt(): LiveData<List<ArtModel>> {
        return artsLiveData
    }

    private fun refreshData(){
        artsLiveData.postValue(arts)
    }
}