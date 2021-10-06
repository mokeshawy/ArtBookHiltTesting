package com.example.artbookhilttesting.viewmodel.adddetailsviewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.artbookhilttesting.constTest.MainCoroutineRule
import com.example.artbookhilttesting.constTest.getOrAwaitValue
import com.example.artbookhilttesting.constants.Status
import com.example.artbookhilttesting.repo.FakeAddArtsDetailsRepository
import com.example.artbookhilttesting.ui.fragment.adddetailsfragment.viewmodel.AddArtDetailsViewModel
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddArtDetailsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    private lateinit var viewModel : AddArtDetailsViewModel

    @Before
    fun setUp(){
        // Test Doubles
        viewModel = AddArtDetailsViewModel(FakeAddArtsDetailsRepository())
    }

    @Test
    fun `insert art without year returns error`(){
        viewModel.makeArt("Car" , "BMW" ,"")
       val value = viewModel.insertArtMsg.getOrAwaitValue()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art without name returns error`(){
        viewModel.makeArt("" , "BMW" ,"1880")
        val value = viewModel.insertArtMsg.getOrAwaitValue()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art without artis name returns error`(){
        viewModel.makeArt("Car" , "" ,"1880")
        val value = viewModel.insertArtMsg.getOrAwaitValue()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }
}