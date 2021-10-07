package com.example.artbookhilttesting.roomdb

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.example.artbookhilttesting.consttest.getOrAwaitValue
import com.example.artbookhilttesting.model.ArtModel
import com.example.artbookhilttesting.room.ArtDao
import com.example.artbookhilttesting.room.ArtDatabase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@SmallTest
@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class ArtDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ArtDatabase
    private lateinit var dao : ArtDao

    @Before
    fun setUp(){
        val appContext = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            appContext,
            ArtDatabase::class.java).allowMainThreadQueries().build()
        dao = database.artDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertArtTesting() = runBlockingTest{
        val exampleArt = ArtModel("Car" , "BMW" , 1990 , "test.com" , 1)
        dao.insertArt(exampleArt)

        val list = dao.observeArts().getOrAwaitValue()
        assertThat(list).contains(exampleArt)
    }

    @Test
    fun deleteArtTesting() = runBlockingTest{
        val exampleArt = ArtModel("Car" , "BMW" , 1990 , "test.com" , 1)
        dao.deleteArt(exampleArt)

        val list = dao.observeArts().getOrAwaitValue()
        assertThat(list).doesNotContain(exampleArt)
    }
}