package com.example.artbookhilttesting.view

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.example.artbookhilttesting.R
import com.example.artbookhilttesting.launchFragmentInHiltContainer
import com.example.artbookhilttesting.ui.fragment.artsfragment.ArtsFragment
import com.example.artbookhilttesting.ui.fragment.artsfragment.ArtsFragmentDirections
import com.example.artbookhilttesting.ui.fragment.fragmentfactory.ArtFragmentFactory
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ArtFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun testNavigationFromArtToArtDetails(){

        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<ArtsFragment>(factory = fragmentFactory) {
            Navigation.setViewNavController(requireView(),navController)
        }
        Espresso.onView(ViewMatchers.withId(R.id.btn_floating_action)).perform(ViewActions.click())
        Mockito.verify(navController).navigate( R.id.action_artsFragment_to_addArtDetailsFragment )
    }
}