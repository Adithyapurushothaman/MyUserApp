package com.example.myuserapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myuserapp.data.remote.ProductApiService
import com.example.myuserapp.data.remote.RetrofitInstance
import com.example.myuserapp.data.repository.ProductRepository

class ProductViewModelFactory() : ViewModelProvider.Factory {
    private val repository: ProductRepository

    init {
        // Initialize the API and repository here
        val api: ProductApiService // Replace with actual API initialization
        val apiService = RetrofitInstance
        repository = ProductRepository(apiService.api)
    }
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}