package com.example.artbookhilttesting.ui.fragment.adddetailsfragment.repository

import com.example.artbookhilttesting.model.ArtModel
import javax.inject.Inject

class AddArtsDetailsRepository
@Inject
constructor(private val artDao: com.example.artbookhilttesting.repo.FakeAddArtsDetailsRepository): AddArtsDetailsRepositoryInterface {

    /* insert art to room database */
    override suspend fun insertArt(artModel: ArtModel) {
        artDao.insertArt(artModel)
    }
}