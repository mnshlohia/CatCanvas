package com.example.catcanvas.presentation.cat_listings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catcanvas.domain.repository.CatListingRepository
import com.example.catcanvas.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatListingViewmodel @Inject constructor(
    private val repository: CatListingRepository
) : ViewModel() {

    var state by mutableStateOf(CatListingsState())

    init {
        getCatListings()
    }

    private fun getCatListings() {

        viewModelScope.launch {
            repository.getCatListings()
                .collect { result ->
                    when (result) {
                        is Resource.Error -> {

                        }

                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }

                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(
                                    cats = listings
                                )
                            }
                        }
                    }
                }
        }

    }

}