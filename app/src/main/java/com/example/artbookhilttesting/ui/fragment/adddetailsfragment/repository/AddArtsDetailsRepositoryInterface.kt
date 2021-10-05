package com.example.artbookhilttesting.ui.fragment.adddetailsfragment.repository

import com.example.artbookhilttesting.model.ArtModel

interface AddArtsDetailsRepositoryInterface {

    suspend fun insertArt( artModel: ArtModel)

}