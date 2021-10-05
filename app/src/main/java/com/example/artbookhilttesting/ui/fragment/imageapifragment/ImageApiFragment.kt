package com.example.artbookhilttesting.ui.fragment.imageapifragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.artbookhilttesting.adapter.ImageRecyclerAdapter
import com.example.artbookhilttesting.constants.Status
import com.example.artbookhilttesting.databinding.FragmentImageApiBinding
import com.example.artbookhilttesting.ui.fragment.adddetailsfragment.viewmodel.AddArtDetailsViewModel
import com.example.artbookhilttesting.ui.fragment.artsfragment.viewmodel.ArtsViewModel
import com.example.artbookhilttesting.ui.fragment.imageapifragment.viewmodel.ImageApiViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageApiFragment
@Inject
constructor( private val imageRecyclerAdapter: ImageRecyclerAdapter): Fragment() {

    lateinit var binding : FragmentImageApiBinding
    private val imageApiViewModel : ImageApiViewModel by activityViewModels()
    private val addArtDetailsViewModel : AddArtDetailsViewModel by activityViewModels()
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        binding = FragmentImageApiBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.rvResultImage.adapter = imageRecyclerAdapter
        binding.rvResultImage.layoutManager = GridLayoutManager(requireActivity(),3)
        imageRecyclerAdapter.setOnItemClickListener {
            findNavController().popBackStack()
            addArtDetailsViewModel.setSelectedImage(it)
        }

        /* call function subscribeToObserve */
        subscribeToObserve()

        var job : Job? = null
        binding.etSearchImage.addTextChangedListener {
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1000)
                it?.let {
                    if(it.toString().isNotEmpty()){
                        imageApiViewModel.searchForImage(it.toString())
                    }
                }
            }
        }
    }

    private fun subscribeToObserve(){
        imageApiViewModel.images.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS ->{
                    val urls = it.data?.hits?.map { imageResult ->  imageResult.previewURL}
                    imageRecyclerAdapter.images = urls ?: listOf()
                    binding.progressBar?.visibility = View.GONE
                }
                Status.ERROR ->{
                    Toast.makeText(requireContext(),it.message ?: "Error", Toast.LENGTH_LONG).show()
                    binding.progressBar.visibility = View.GONE
                }
                Status.LOADING ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }
}