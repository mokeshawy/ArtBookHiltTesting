package com.example.artbookhilttesting.ui.fragment.artsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.artbookhilttesting.R
import com.example.artbookhilttesting.databinding.FragmentArtsBinding


class ArtsFragment : Fragment() {

    lateinit var binding : FragmentArtsBinding
    private val artsViewModel : ArtsViewModel by activityViewModels()
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
        binding.artsFragment    = artsViewModel

        /* go to add details arts */
        binding.btnFloatingAction.setOnClickListener {
            findNavController().navigate(R.id.action_artsFragment_to_addArtDetailsFragment)
        }
    }
}