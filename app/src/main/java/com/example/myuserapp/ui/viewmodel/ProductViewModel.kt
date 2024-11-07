//package com.example.myuserapp.ui.viewmodel
//
////class ProductViewModel(private val repository: ProductRepository): ViewModel() {
////
////    private val _products = MutableLiveData<List<Product>>()
////    val products: LiveData<List<Product>> get() = _products
////
////    fun fetchProducts(limit: Int, skip: Int) {
////        viewModelScope.launch {
////            val response = repository.getProducts(limit, skip)
////            if (response.isSuccessful) {
////                _products.postValue(response.body()?.products)
////            }
////        }
////    }
//////    fun fetchProducts() {
//////        viewModelScope.launch {
//////            val response = repository.getProducts()
//////            if (response.isSuccessful) {
//////                _products.value = (response.body() ?: emptyList()) as List<Product>?
//////            }
//////        }
//////    }
////}
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.myuserapp.data.models.Product
//import com.example.myuserapp.data.repository.ProductRepository
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//
//class ProductViewModel(private val repository: ProductRepository) : ViewModel() {
//    private val _products = MutableStateFlow<List<Product>>(emptyList())
//    val products: StateFlow<List<Product>> = _products
//
//    private val _selectedProduct = MutableStateFlow<Product?>(null)
//    val selectedProduct: StateFlow<Product?> = _selectedProduct
//
//    private var currentPage = 0
//    private val limit = 10
//
//    init {
//        loadProducts()
//    }
//
//    fun loadProducts() {
//        viewModelScope.launch {
//            val response = repository.getProducts(limit, currentPage * limit)
//            response.body()?.products?.let { newProducts ->
//                _products.value += newProducts
//                currentPage++
//            }
//        }
//    }
//
//    fun getProductDetails(id: Int) {
//        viewModelScope.launch {
//            val response = repository.getProductDetails(id)
//            _selectedProduct.value = response.body()
//        }
//    }
//}
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myuserapp.data.models.Product
import com.example.myuserapp.data.models.ProductDetail
import com.example.myuserapp.data.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

//class ProductViewModel(private val repository: ProductRepository) : ViewModel() {
//    val products: Flow<PagingData<Product>> = repository.getProductsPagingData().cachedIn(viewModelScope)
//    private val _selectedProduct = MutableStateFlow<Product?>(null)
//
//    fun getProductDetails(id: Int) {
//        viewModelScope.launch {
//            val response = repository.getProductDetails(id)
//            _selectedProduct.value = response.body()
//        }
//    }
//}
class ProductViewModel(private val repository: ProductRepository) : ViewModel() {
    val products: Flow<PagingData<Product>> = repository.getProductsPagingData().cachedIn(viewModelScope)

    // Define getProductDetails to fetch product by ID
    suspend fun getProductDetails(productId: Int): Response<ProductDetail> {
        return repository.getProductDetails(productId)
    }
}
