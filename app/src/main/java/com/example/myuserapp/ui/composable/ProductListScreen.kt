package com.example.myuserapp.ui.composable


import ProductDetailScreen
import ProductViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.example.myuserapp.data.models.Product

@Composable
fun ProductListScreen(viewModel: ProductViewModel, navController: NavController) {
    val products = viewModel.products.collectAsLazyPagingItems()

    LazyColumn {
        items(products.itemCount) { index ->
            val product = products[index]
            product?.let { ProductListItem(
                product = it,
                onItemClick = { ProductDetailScreen(productId = it.id,viewModel) }
            ) }
        }
        products.apply {
            when (loadState.append) {
                is LoadState.Loading -> {
                    item { CircularProgressIndicator(modifier = Modifier.padding(16.dp)) }
                }

                is LoadState.Error -> {
                    item { Text("Failed to load more products", Modifier.padding(16.dp)) }
                }

                is LoadState.NotLoading -> TODO()
            }
        }
    }
}

@Composable
fun ProductListItem(product: Product, onItemClick: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = rememberImagePainter(product.thumbnail),
            contentDescription = product.title,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(product.title, style = MaterialTheme.typography.titleSmall)
            Text("$${product.price}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
