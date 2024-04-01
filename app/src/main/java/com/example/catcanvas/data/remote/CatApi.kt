package com.example.catcanvas.data.remote

import com.example.catcanvas.domain.model.CatListingItem
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {

    @GET("search?limit=10")
    suspend fun getListings(): List<CatListingItem>

    companion object{
        const val BASE_URL = "https://api.thecatapi.com/v1/images/"
    }

}