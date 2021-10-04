package com.example.artbookhilttesting.api

import com.example.artbookhilttesting.constants.Const
import com.example.artbookhilttesting.model.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPoint {

    @GET(Const.END_POINT)
    suspend fun imageSearch(@Query(Const.QUERY_Q) searchQuery : String,
                            @Query(Const.QUERY_KEY) apiKey : String = Const.API_KEY
    ) : Response<ImageResponse>
}