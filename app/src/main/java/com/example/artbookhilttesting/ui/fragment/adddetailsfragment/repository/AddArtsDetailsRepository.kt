package com.example.artbookhilttesting.ui.fragment.adddetailsfragment.repository

import com.example.artbookhilttesting.model.ArtModel
import com.example.artbookhilttesting.room.ArtDao
import javax.inject.Inject

class AddArtsDetailsRepository
@Inject
constructor(private val artDao: ArtDao ): AddArtsDetailsRepositoryInterface {

    /* insert art to room database */
    override suspend fun insertArt(artModel: ArtModel) {
        artDao.insertArt(artModel)
    }
}