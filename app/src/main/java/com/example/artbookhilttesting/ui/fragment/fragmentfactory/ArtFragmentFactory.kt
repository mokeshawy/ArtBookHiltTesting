package com.example.artbookhilttesting.ui.fragment.fragmentfactory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.example.artbookhilttesting.ui.fragment.adddetailsfragment.AddArtDetailsFragment
import javax.inject.Inject

class ArtFragmentFactory
@Inject
constructor( private val glide : RequestManager) : FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            AddArtDetailsFragment::class.java.name -> AddArtDetailsFragment(glide)
            else -> instantiate(classLoader, className)
        }
    }
}