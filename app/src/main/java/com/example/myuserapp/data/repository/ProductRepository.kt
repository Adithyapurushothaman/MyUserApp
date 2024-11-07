package com.example.myuserapp.data.repository


//class ProductRepository {
//    private val apiService = RetrofitInstance
////    private val userData = MutableStateFlow<ProductResponse?>(null)
//
//
//    suspend fun getProducts(limit: Int, skip: Int) = apiService.service.getProducts(limit, skip)
//    suspend fun getProductDetails(id: Int) = apiService.service.getProductDetails(id)
//
//}

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myuserapp.data.models.Product
import com.example.myuserapp.data.models.ProductDetail
import com.example.myuserapp.data.remote.ProductApiService
import com.example.myuserapp.data.remote.RetrofitInstance
import retrofit2.Response

class ProductPagingSource(private val api: ProductApiService) : PagingSource<Int, Product>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val currentPage = params.key ?: 0
            val response = api.getProducts(limit = params.loadSize, skip = currentPage * params.loadSize)

            LoadResult.Page(
                data = response.body()!!.products,
                prevKey = if (currentPage == 0) null else currentPage - 1,
                nextKey = if (response.body()!!.products.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition
    }
}


class ProductRepository(private val api: ProductApiService) {
    private val apiService = RetrofitInstance

    fun getProductsPagingData() = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { ProductPagingSource(api) }
    ).flow

    suspend fun getProducts(limit: Int, skip: Int) = apiService.api.getProducts(limit, skip)
    suspend fun getProductDetails(id: Int): Response<ProductDetail> {
        return apiService.api.getProductDetails(id)
    }

}
