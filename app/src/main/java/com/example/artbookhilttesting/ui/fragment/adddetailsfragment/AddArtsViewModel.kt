package com.example.artbookhilttesting.ui.fragment.adddetailsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artbookhilttesting.constants.Resource
import com.example.artbookhilttesting.model.ArtModel
import com.example.artbookhilttesting.model.ImageResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddArtsViewModel
@Inject
constructor( private val addArtDetailsRepository: AddArtDetailsRepository): ViewModel() {

    /* get artList from repository */
    val artList = addArtDetailsRepository.getArt()

    /* get image from imageResponse */
    val images = MutableLiveData<Resource<ImageResponse>>()

    /* selected image as live data */
    val selectedImage = MutableLiveData<String>()

    /* insert art message as live data*/
    var insertArtMsg = MutableLiveData<Resource<ArtModel>>()


    /* rest insert image */
    fun restInsertInsertMsg(){
        insertArtMsg = MutableLiveData<Resource<ArtModel>>()
    }

    /* selected image */
    fun setSelectedImage( url : String){
        selectedImage.postValue(url)
    }

    /* delete art */
    fun deleteArt(artModel: ArtModel) = viewModelScope.launch{
        addArtDetailsRepository.deleteArt(artModel)
    }

    /* insertArt */
    fun insertArt( artModel: ArtModel) = viewModelScope.launch {
        addArtDetailsRepository.insertArt(artModel)
    }

    /* get art from remote */
    fun makeArt( name : String , artistName : String ,year : String ){
        if( name.isEmpty() || artistName.isEmpty() || year.isEmpty()){
            insertArtMsg.postValue(Resource.error("Enter name , artist , year", null))
        }
        val yearInt = try {
            year.toInt()
        }catch (e:Exception){
            insertArtMsg.postValue(Resource.error("Year should be number",null))
            return
        }
        val art = ArtModel(name ,artistName , yearInt, selectedImage.value?: "" )
        insertArt(art)
        setSelectedImage("")
        insertArtMsg.postValue(Resource.success(art))
    }


    /* search for image */
    fun searchForImage( searchString : String ) {
        if(searchString.isEmpty()){
            return
        }
        images.value = Resource.loading(null)
        viewModelScope.launch {
            val response = addArtDetailsRepository.searchImage(searchString)
            images.value = response
        }
    }
}