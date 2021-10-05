package com.example.artbookhilttesting.ui.fragment.imageapifragment.repository

import androidx.lifecycle.LiveData
import com.example.artbookhilttesting.api.EndPoint
import com.example.artbookhilttesting.constants.Resource
import com.example.artbookhilttesting.model.ArtModel
import com.example.artbookhilttesting.model.ImageResponse
import com.example.artbookhilttesting.room.ArtDao
import javax.inject.Inject

class ImageApiRepository
@Inject
constructor( private val endPoint: EndPoint ): ImageApiRepositoryInterface {



    /* search  image from api  */
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