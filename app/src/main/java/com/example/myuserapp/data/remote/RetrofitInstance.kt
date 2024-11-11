package com.example.myuserapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(" https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: ProductApiService by lazy { retrofit.create(ProductApiService::class.java) }
}


