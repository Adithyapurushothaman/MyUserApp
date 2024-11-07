package com.example.myuserapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//object RetrofitInstance1 {
////    const val BASE_URL = " https://dummyjson.com/products?limit=10&skip=0"
//
//    private val retrofit: Retrofit =
//        Retrofit.Builder().baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    var service: ProductApiService = retrofit.create(ProductApiService::class.java)
//
//    companion object{
//        const val BASE_URL = " https://dummyjson.com/products?limit=10&skip=0"
//    }
//
//}

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(" https://dummyjson.com/products?limit=10&skip=0")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: ProductApiService by lazy { retrofit.create(ProductApiService::class.java) }
}
