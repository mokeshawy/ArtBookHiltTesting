package com.example.artbookhilttesting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.artbookhilttesting.databinding.FragmentImageApiBinding
import com.example.artbookhilttesting.ui.fragment.imageapifragment.ImageApiViewModel

class ImageApiFragment : Fragment() {

    lateinit var binding : FragmentImageApiBinding
    private val imageApiViewModel : ImageApiViewModel by viewModels()
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