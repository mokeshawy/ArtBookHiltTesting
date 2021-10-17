package com.example.artbookhilttesting.roomdb



import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.artbookhilttesting.getOrAwaitValue
import com.example.artbookhilttesting.model.ArtModel
import com.example.artbookhilttesting.room.ArtDao
import com.example.artbookhilttesting.room.ArtDatabase
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named


@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ArtDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("testDatabase")
    lateinit var database : ArtDatabase

    private lateinit var dao : ArtDao

    @Before
    fun setUp(){
       /* val appContext = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            appContext,
            ArtDatabase::class.java).allowMainThreadQueries().build() */

        hiltRule.inject()
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