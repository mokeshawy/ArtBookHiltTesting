package com.example.artbookhilttesting.viewmodel.artsviewmodeltest

import com.example.artbookhilttesting.repo.FakeArtsRepository
import com.example.artbookhilttesting.ui.fragment.artsfragment.viewmodel.ArtsViewModel
import org.junit.Before


class ArtsViewModelTest {

    private lateinit var viewModel : ArtsViewModel

    @Before
    fun setUp(){
        // Test Doubles
        viewModel = ArtsViewModel(FakeArtsRepository())
    }

}