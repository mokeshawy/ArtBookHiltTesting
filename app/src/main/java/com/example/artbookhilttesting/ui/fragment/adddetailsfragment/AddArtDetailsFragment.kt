package com.example.artbookhilttesting.ui.fragment.adddetailsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.artbookhilttesting.R
import com.example.artbookhilttesting.constants.Status
import com.example.artbookhilttesting.databinding.FragmentAddArtDetailsBinding
import com.example.artbookhilttesting.ui.fragment.adddetailsfragment.viewmodel.AddArtDetailsViewModel
import com.example.artbookhilttesting.ui.fragment.artsfragment.viewmodel.ArtsViewModel
import javax.inject.Inject

class AddArtDetailsFragment
@Inject
constructor(private val glide : RequestManager): Fragment() {

    lateinit var binding                : FragmentAddArtDetailsBinding
    private val addArtDetailsViewModel  : AddArtDetailsViewModel by activityViewModels()
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddArtDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* connect with view model */
        binding.lifecycleOwner = this
        binding.addArtsDetailsFragment = addArtDetailsViewModel

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

        binding.saveButton.setOnClickListener {
            addArtDetailsViewModel.makeArt(binding.etEnterName.text.toString(),binding.etEnterArts.text.toString(),binding.etEnterYear.text.toString())
        }
        /* call function subscribeToObserver */
        subscribeToObserver()
    }

    /* function subscribeToObserver */
    private fun subscribeToObserver(){
        addArtDetailsViewModel.selectedImage.observe(viewLifecycleOwner, Observer { url ->
            binding?.let {
                glide.load(url).into(it.artImageView)
            }
        })

        addArtDetailsViewModel.insertArtMsg.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS ->{
                    Toast.makeText(requireActivity(),"Success",Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                    addArtDetailsViewModel.restInsertMsg()
                }
                Status.ERROR ->{
                    Toast.makeText(requireActivity(),it.message ?: "Error",Toast.LENGTH_SHORT).show()
                }
                Status.LOADING ->{

                }
            }
        })
    }
}