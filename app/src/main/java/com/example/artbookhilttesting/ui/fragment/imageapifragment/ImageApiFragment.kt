package com.example.artbookhilttesting.ui.fragment.imageapifragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.RequestManager
import com.example.artbookhilttesting.adapter.ImageRecyclerAdapter
import com.example.artbookhilttesting.databinding.FragmentImageApiBinding
import javax.inject.Inject

class ImageApiFragment
@Inject
constructor( private val imageRecyclerAdapter: ImageRecyclerAdapter): Fragment() {

    lateinit var binding : FragmentImageApiBinding
    private val imageApiViewModel : ImageApiViewModel by activityViewModels()
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        binding = FragmentImageApiBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* connect with viewModel */
        binding.lifecycleOwner      = this
        binding.imageApiFragment    = imageApiViewModel


    }
}