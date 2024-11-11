package com.example.myuserapp.ui.viewmodel//package com.example.myuserapp.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myuserapp.data.models.Product
import com.example.myuserapp.data.models.ProductDetail
import com.example.myuserapp.data.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


class ProductViewModel(private val repository: ProductRepository) : ViewModel() {
    val products: Flow<PagingData<Product>> = repository.getProductsPagingData().cachedIn(viewModelScope)

    // Define getProductDetails to fetch product by ID
    suspend fun getProductDetails(productId: Int): ProductDetail {
        return repository.getProductDetails(productId)
    }
}



