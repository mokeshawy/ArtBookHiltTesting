package com.example.artbookhilttesting.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.example.artbookhilttesting.R
import com.example.artbookhilttesting.getOrAwaitValue
import com.example.artbookhilttesting.launchFragmentInHiltContainer
import com.example.artbookhilttesting.model.ArtModel
import com.example.artbookhilttesting.repo.FakeAddArtsDetailsRepositoryAndroid
import com.example.artbookhilttesting.repo.FakeArtsRepositoryAndroid
import com.example.artbookhilttesting.ui.fragment.adddetailsfragment.AddArtDetailsFragment
import com.example.artbookhilttesting.ui.fragment.adddetailsfragment.viewmodel.AddArtDetailsViewModel
import com.example.artbookhilttesting.ui.fragment.artsfragment.viewmodel.ArtsViewModel
import com.example.artbookhilttesting.ui.fragment.fragmentfactory.ArtFragmentFactory
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject
import javax.inject.Named

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class AddDetailsFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory


    lateinit var artsViewModel : ArtsViewModel

    @Before
    fun setUp(){
       hiltRule.inject()
    }

    @Test
    fun testNavigationFromArtDetailsToImageApi(){
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<AddArtDetailsFragment>(factory = fragmentFactory){
            Navigation.setViewNavController(requireView(),navController)
        }
        Espresso.onView(withId(R.id.art_image_view)).perform(click())
        Mockito.verify(navController).navigate(R.id.action_addArtDetailsFragment_to_imgaeApiFragment)
    }

    @Test
    fun testOnBackPressed(){
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<AddArtDetailsFragment>(factory = fragmentFactory){
            Navigation.setViewNavController(requireView(),navController)
        }
        Espresso.pressBack()
        Mockito.verify(navController).popBackStack()
    }

    @Test
    fun testSave(){
        val testViewModel = ArtsViewModel(FakeArtsRepositoryAndroid())
        launchFragmentInHiltContainer<AddArtDetailsFragment>(
            factory = fragmentFactory
        ){
            artsViewModel = testViewModel
        }

        Thread {
            onView(withId(R.id.et_enter_name)).perform(replaceText("Mona Lisa"))
            onView(withId(R.id.et_enter_arts)).perform(replaceText("Da Vinci"))
            onView(withId(R.id.et_enter_year)).perform(replaceText("1700"))
            onView(withId(R.id.saveButton)).perform(click())

            assertThat(testViewModel.artList.getOrAwaitValue()).contains(
                ArtModel(
                    "Mona Lisa",
                    "Da Vinci",
                    1700,"")
            )
        }.start()
    }
}