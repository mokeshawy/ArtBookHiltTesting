package com.example.artbookhilttesting.ui.fragment.artsfragment.repository

import androidx.lifecycle.LiveData
import com.example.artbookhilttesting.api.EndPoint
import com.example.artbookhilttesting.constants.Resource
import com.example.artbookhilttesting.model.ArtModel
import com.example.artbookhilttesting.model.ImageResponse
import com.example.artbookhilttesting.room.ArtDao
import javax.inject.Inject

class ArtsRepository
@Inject
constructor(private val artDao: ArtDao ): ArtsRepositoryInterface {

    /* delete art from room database */
    override suspend fun deleteArt(artModel: ArtModel) {
        artDao.deleteArt(artModel)
    }

    /* get art list from room */
    override fun getArt(): LiveData<List<ArtModel>> {
        return artDao.observeArts()
    }

}