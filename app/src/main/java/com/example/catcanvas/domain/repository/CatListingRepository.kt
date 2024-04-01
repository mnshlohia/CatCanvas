package com.example.catcanvas.domain.repository

import com.example.catcanvas.data.remote.CatApi
import com.example.catcanvas.domain.model.CatListingItem
import com.example.catcanvas.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CatListingRepository {

    suspend fun getCatListings(): Flow<Resource<List<CatListingItem>>>
}