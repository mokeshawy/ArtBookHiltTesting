package com.example.artbookhilttesting.repo

import androidx.lifecycle.MutableLiveData
import com.example.artbookhilttesting.model.ArtModel
import com.example.artbookhilttesting.ui.fragment.adddetailsfragment.repository.AddArtsDetailsRepositoryInterface

class FakeAddArtsDetailsRepository : AddArtsDetailsRepositoryInterface {

    private val arts = mutableListOf<ArtModel>()
    private val artsLiveData = MutableLiveData<List<ArtModel>>(arts)

    override suspend fun insertArt(artModel: ArtModel) {
        arts.add(artModel)
    }
}