package com.example.catcanvas.domain.repository

import com.example.catcanvas.data.remote.CatApi
import com.example.catcanvas.domain.model.CatListingItem
import com.example.catcanvas.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatListingRepositoryImpl @Inject constructor(
    private val api: CatApi,
) : CatListingRepository {
    override suspend fun getCatListings(
    ): Flow<Resource<List<CatListingItem>>> {

        return flow {
            emit(Resource.Loading(true))

            val remoteListings = try {
                api.getListings()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data"))
                null
            }

            remoteListings?.let { listings ->
                emit(Resource.Success(listings))
                emit(Resource.Loading(false))
            }

        }
    }
}