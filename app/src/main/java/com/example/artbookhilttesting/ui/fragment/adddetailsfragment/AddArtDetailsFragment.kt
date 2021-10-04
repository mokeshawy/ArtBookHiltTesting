package com.example.artbookhilttesting.ui.fragment.adddetailsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.artbookhilttesting.R
import com.example.artbookhilttesting.databinding.FragmentAddArtDetailsBinding

class AddArtDetailsFragment : Fragment() {

    lateinit var binding            : FragmentAddArtDetailsBinding
    private val addArtsViewModel    : AddArtsViewModel by viewModels()
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddArtDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* connect with view model */
        binding.lifecycleOwner = this
        binding.adArtsDetailsFragment = addArtsViewModel
    }
}