package com.example.catcanvas.presentation.cat_listings

import com.example.catcanvas.domain.model.CatListingItem

data class CatListingsState(
    val cats: List<CatListingItem> = emptyList(),
    val isLoading: Boolean = false
)