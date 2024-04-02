package com.example.catcanvas

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.catcanvas.domain.model.CatListingItem
import com.example.catcanvas.domain.repository.CatListingRepositoryImpl
import com.example.catcanvas.presentation.cat_listings.CatListingViewmodel
import com.example.catcanvas.ui.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CatListingViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()


    @Mock
    private lateinit var repository: CatListingRepositoryImpl

    private lateinit var viewModel: CatListingViewmodel

    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = CatListingViewmodel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the Main dispatcher to the original Main dispatcher
    }

    private fun getMockedData(): List<CatListingItem> {

        return listOf(
            CatListingItem(
                id = "6",
                url = "https://28.media.tumblr.com/tumblr_ks1a707b1b1qa9hjso1_1280.png",
                width = 507,
                height = 375
            ),
            CatListingItem(
                id = "j2",
                url = "https://cdn2.thecatapi.com/images/j2.jpg",
                width = 640,
                height = 425
            ),
            CatListingItem(
                id = "5s1",
                url = "https://cdn2.thecatapi.com/images/5s1.jpg",
                width = 450,
                height = 577
            ),
            CatListingItem(
                id = "9l3",
                url = "https://cdn2.thecatapi.com/images/9l3.jpg",
                width = 2868,
                height = 1917
            ),
            CatListingItem(
                id = "b7k",
                url = "https://cdn2.thecatapi.com/images/b7k.jpg",
                width = 500,
                height = 332
            ),
            CatListingItem(
                id = "d75",
                url = "https://cdn2.thecatapi.com/images/d75.jpg",
                width = 500,
                height = 313
            ),
            CatListingItem(
                id = "dhv",
                url = "https://cdn2.thecatapi.com/images/dhv.jpg",
                width = 1002,
                height = 1512
            ),
            CatListingItem(
                id = "e2d",
                url = "https://cdn2.thecatapi.com/images/e2d.jpg",
                width = 466,
                height = 700
            ),
            CatListingItem(
                id = "MTY3MDQ3Mw",
                url = "https://cdn2.thecatapi.com/images/MTY3MDQ3Mw.jpg",
                width = 640,
                height = 429
            ),
            CatListingItem(
                id = "zXYdWjjy4",
                url = "https://cdn2.thecatapi.com/images/zXYdWjjy4.jpg",
                width = 750,
                height = 750
            )
        )
    }

    @Test
    fun `get cat listings success`() = runTest {
        val mockData = getMockedData()

        Mockito.`when`(repository.getCatListings()).thenReturn(flow {
            emit(Resource.Success(mockData))
        })

        viewModel.getCatListings()

        assert(viewModel.state.cats.isNotEmpty())
        assert(!viewModel.state.isLoading)
    }


    @Test
    fun `get cat listings loading`() = runTest {
        Mockito.`when`(repository.getCatListings()).thenReturn(flow {
            emit(Resource.Loading(true))
        })
        viewModel.getCatListings()
        assert(viewModel.state.isLoading)
    }

    @Test
    fun `getCatListings emits error state`() = runTest {
        Mockito.`when`(repository.getCatListings()).thenReturn(
            flow { emit(Resource.Error(message = "Couldn't load data")) }
        )

        viewModel.getCatListings()

        assertTrue(viewModel.state.cats.isEmpty())
        assertEquals(false, viewModel.state.isLoading) // Assuming you handle loading false on error
    }

}
