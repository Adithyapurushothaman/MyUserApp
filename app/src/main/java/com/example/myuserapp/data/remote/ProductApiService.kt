package com.example.myuserapp.data.remote

import com.example.myuserapp.data.models.ProductDetail
import com.example.myuserapp.data.models.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ProductApiService {
    @GET("products")
    suspend fun getProducts(@Query("limit") limit: Int, @Query("skip") skip: Int): ProductResponse

    @GET("products/{id}")
    suspend fun getProductDetails(@Path("id") productId: Int): ProductDetail
}


