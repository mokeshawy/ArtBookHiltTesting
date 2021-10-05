package com.example.artbookhilttesting.ui.fragment.adddetailsfragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artbookhilttesting.constants.Resource
import com.example.artbookhilttesting.model.ArtModel
import com.example.artbookhilttesting.model.ImageResponse
import com.example.artbookhilttesting.ui.fragment.adddetailsfragment.repository.AddArtsDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddArtDetailsViewModel
@Inject
constructor( private val addArtsDetailsRepository: AddArtsDetailsRepository ): ViewModel() {

    val etName          = MutableLiveData<String>()
    val etArtistName    = MutableLiveData<String>()
    val etYear          = MutableLiveData<String>()


    /* selected image as live data */
    val selectedImage = MutableLiveData<String>()

    /* insert art message as live data*/
    var insertArtMsg = MutableLiveData<Resource<ArtModel>>()


    /* rest insert image */
    fun restInsertMsg(){
        insertArtMsg = MutableLiveData<Resource<ArtModel>>()
    }

    /* selected image */
    fun setSelectedImage( url : String){
        selectedImage.postValue(url)
    }

    /* insertArt */
    fun insertArt( artModel: ArtModel) = viewModelScope.launch {
        addArtsDetailsRepository.insertArt(artModel)
    }

    /* get art from remote */
    fun makeArt( name : String , artistName : String  , year : String ){
        if( name.isEmpty() || artistName.isEmpty() || year.isEmpty()){
            insertArtMsg.postValue(Resource.error("Enter name , artist , year", null))
            return
        }
        val yearInt = try {
            year.toInt()
        }catch (e:Exception){
            insertArtMsg.postValue(Resource.error("Year should be number",null))
            return
        }
        val art = ArtModel(etName.value!! ,etArtistName.value!! , yearInt, selectedImage.value?: "" )
        insertArt(art)
        setSelectedImage("")
        insertArtMsg.postValue(Resource.success(art))
    }
}