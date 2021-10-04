package com.example.artbookhilttesting.ui.fragment.adddetailsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.artbookhilttesting.R
import com.example.artbookhilttesting.databinding.FragmentAddArtDetailsBinding
import javax.inject.Inject

class AddArtDetailsFragment
@Inject
constructor( val glide : RequestManager): Fragment() {

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

        binding.artImageView.setOnClickListener {
            findNavController().navigate(R.id.action_addArtDetailsFragment_to_imgaeApiFragment)
        }

        /* handle on back pressed of fragment */
        val callBack = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)



    }
}