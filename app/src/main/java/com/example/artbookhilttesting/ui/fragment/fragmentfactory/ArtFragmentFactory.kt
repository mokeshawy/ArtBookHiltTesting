package com.example.artbookhilttesting.ui.fragment.fragmentfactory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.example.artbookhilttesting.adapter.ArtRecyclerAdapter
import com.example.artbookhilttesting.adapter.ImageRecyclerAdapter
import com.example.artbookhilttesting.ui.fragment.adddetailsfragment.AddArtDetailsFragment
import com.example.artbookhilttesting.ui.fragment.artsfragment.ArtsFragment
import com.example.artbookhilttesting.ui.fragment.imageapifragment.ImageApiFragment
import javax.inject.Inject

class ArtFragmentFactory
@Inject
constructor(
    private val artRecyclerAdapter: ArtRecyclerAdapter,
    private val glide : RequestManager,
    private val imageRecyclerAdapter: ImageRecyclerAdapter
    ) : FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){

            /* inject artRecyclerAdapter in artsFragment */
            ArtsFragment::class.java.name          -> ArtsFragment(artRecyclerAdapter)

            /* inject glide in addArtDetailsFragment */
            AddArtDetailsFragment::class.java.name -> AddArtDetailsFragment(glide)

            /* inject imageRecyclerAdapter in imageApiFragment */
            ImageApiFragment::class.java.name -> ImageApiFragment(imageRecyclerAdapter)

            else -> super.instantiate(classLoader, className)
        }
    }
}