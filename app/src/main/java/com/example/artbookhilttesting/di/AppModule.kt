package com.example.artbookhilttesting.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.artbookhilttesting.R
import com.example.artbookhilttesting.api.EndPoint
import com.example.artbookhilttesting.constants.Const
import com.example.artbookhilttesting.room.ArtDao
import com.example.artbookhilttesting.room.ArtDatabase
import com.example.artbookhilttesting.ui.fragment.adddetailsfragment.repository.AddArtsDetailsRepositoryInterface
import com.example.artbookhilttesting.ui.fragment.artsfragment.repository.ArtsRepository
import com.example.artbookhilttesting.ui.fragment.artsfragment.repository.ArtsRepositoryInterface
import com.example.artbookhilttesting.ui.fragment.imageapifragment.repository.ImageApiRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /* provide roomDB */
    @Singleton
    @Provides
    fun injectRoomDatabase( @ApplicationContext context: Context) = Room.databaseBuilder(
        context,ArtDatabase::class.java,Const.ROOM_DB_NAME
    ).build()

    @Singleton
    @Provides
    fun injectDao( database: ArtDatabase ) = database.artDao()



    /* retrofit provides */
    @Singleton
    @Provides
    fun injectEndPoint() : EndPoint{
       return Retrofit.Builder().baseUrl(Const.BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .build().create(EndPoint::class.java)
    }



    /* provide AddArtDetailsRepository */
    @Singleton
    @Provides
    fun injectNormalArtsRepo( artDao: ArtDao , api : EndPoint) = ArtsRepository( artDao , api ) as ArtsRepositoryInterface

    /* provide AddArtDetailsRepository */
    @Singleton
    @Provides
    fun injectNormalAddArtDetailsRepo( artDao: ArtDao , api : EndPoint) = ArtsRepository( artDao , api ) as AddArtsDetailsRepositoryInterface

    /* provide AddArtDetailsRepository */
    @Singleton
    @Provides
    fun injectNormalImageApiRepo( artDao: ArtDao , api : EndPoint) = ArtsRepository( artDao , api ) as ImageApiRepositoryInterface

    /* provide adapter */
    @Singleton
    @Provides
    fun injectGlide( @ApplicationContext context: Context ) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
        )

}