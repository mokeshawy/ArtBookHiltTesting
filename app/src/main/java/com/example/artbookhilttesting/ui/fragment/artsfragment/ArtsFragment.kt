package com.example.artbookhilttesting.ui.fragment.artsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.artbookhilttesting.R
import com.example.artbookhilttesting.adapter.ArtRecyclerAdapter
import com.example.artbookhilttesting.databinding.FragmentArtsBinding
import com.example.artbookhilttesting.ui.fragment.artsfragment.viewmodel.ArtsViewModel
import javax.inject.Inject


class ArtsFragment
@Inject
constructor(
    val artRecyclerAdapter: ArtRecyclerAdapter

    ) : Fragment() {

    lateinit var binding : FragmentArtsBinding
    private val artViewModel : ArtsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        binding = FragmentArtsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* connect with viewModel */
        binding.lifecycleOwner  = this
        binding.artsFragment    = artViewModel

        /* go to add details arts */
        binding.btnFloatingAction.setOnClickListener {
            findNavController().navigate(R.id.action_artsFragment_to_addArtDetailsFragment)
        }

        /* create swipe delete of item ..*/
        val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove( recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val layoutPosition = viewHolder.layoutPosition
                val selectArt = artRecyclerAdapter.arts[layoutPosition]
                artViewModel.deleteArt(selectArt)
            }
        }

        /* call function subscribeToObservers*/
        subscribeToObservers()

        /* handle operation of recycler view */
        binding.rvArtsBook.adapter = artRecyclerAdapter
        binding.rvArtsBook.layoutManager = LinearLayoutManager(requireActivity())
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.rvArtsBook)

    }

    /* function of subscribeToObservers */
    private fun subscribeToObservers(){
        artViewModel.artList.observe(viewLifecycleOwner, Observer {
            artRecyclerAdapter.arts = it
        })
    }
}