package com.example.artbookhilttesting.ui.fragment.artsfragment

import androidx.lifecycle.LiveData
import com.example.artbookhilttesting.api.EndPoint
import com.example.artbookhilttesting.constants.Resource
import com.example.artbookhilttesting.model.ArtModel
import com.example.artbookhilttesting.model.ImageResponse
import com.example.artbookhilttesting.room.ArtDao
import javax.inject.Inject

class ArtRepository
@Inject
constructor(private val artDao: ArtDao,
            private val endPoint: EndPoint): ArtRepositoryInterface{

    /* insert art to room database */
    override suspend fun insertArt(artModel: ArtModel) {
        artDao.insertArt(artModel)
    }

    /* delete art from room database */
    override suspend fun deleteArt(artModel: ArtModel) {
        artDao.deleteArt(artModel)
    }

    /* get art list from api */
    override fun getArt(): LiveData<List<ArtModel>> {
        return artDao.observeArts()
    }

    /* search  image */
    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return try {
            val response = endPoint.imageSearch(imageString)
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                }?: Resource.error("Error",null)
            }else{
                Resource.error("Error",null)
            }
        }catch (e:Exception){
            Resource.error("No data",null)
        }
    }
}