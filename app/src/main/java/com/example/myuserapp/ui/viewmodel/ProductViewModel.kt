package com.example.myuserapp.ui.viewmodel//package com.example.myuserapp.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myuserapp.data.models.ProductDetail
import com.example.myuserapp.data.repository.ProductRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn


class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _currentPage = mutableIntStateOf(0)

    private val pager = repository.getProductsPagingData()
    val products = pager.cachedIn(viewModelScope).stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        PagingData.empty()
    )
     // Track current page

    val currentPage: State<Int> = _currentPage

//    val products = repository.getProductsPagingData().cachedIn(viewModelScope)

    fun setPage(page: Int) {
        _currentPage.intValue = page
    }

    // Define getProductDetails to fetch product by ID
    suspend fun getProductDetails(productId: Int): ProductDetail {
        return repository.getProductDetails(productId)
    }
}



