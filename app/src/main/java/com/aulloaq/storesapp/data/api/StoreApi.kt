package com.aulloaq.storesapp.data.api

import com.aulloaq.storesapp.domain.model.Store
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface StoreApi {
    @GET("stores")
    suspend fun getStoreList(
        @Header("Authorization") token: String,
        @Header("X-Company-UUID") company: String,
        @Path("per_page") items: Int,
        @Path("page") page: Int
    ): Response<List<Store>>
}