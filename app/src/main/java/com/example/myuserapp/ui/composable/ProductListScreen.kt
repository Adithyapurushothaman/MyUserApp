package com.example.myuserapp.ui.composable


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.example.myuserapp.data.models.Product
import com.example.myuserapp.ui.viewmodel.ProductViewModel

@Composable
fun ProductListScreen(viewModel: ProductViewModel, navController: NavController) {

    val currentPage = viewModel.currentPage.value
    val products = viewModel.products.collectAsLazyPagingItems()

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        LazyColumn(modifier = Modifier.weight(1F)) {
            items(products.itemCount) { index ->
                val product = products[index]
                product?.let { ProductListItem(
                    product = it,
                    onItemClick = { navController.navigate("productDetail/${it.id}") }
                ) }
            }
            products.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { CircularProgressIndicator(modifier = Modifier.padding(16.dp)) }
                    }
                    loadState.append is LoadState.Loading -> {
                        item { CircularProgressIndicator(modifier = Modifier.padding(16.dp)) }
                    }
                    loadState.append is LoadState.Error -> {
                        item { Text("Failed to load more products", Modifier.padding(16.dp)) }
                    }
                    loadState.append is LoadState.NotLoading -> {
                        item { Text("No more products to load", Modifier.padding(16.dp)) }
                    }
                }
            }

        }
        val totalPages = products.itemCount / 10 + if (products.itemCount % 10 == 0) 0 else 1
        if (totalPages > 1) {
            PageNumberBar(
                currentPage = currentPage,
                totalPages = totalPages,
                onPageSelected = { page ->
                        viewModel.setPage(page)
                    products.refresh()
                    }
            )
        }
    }
}

@Composable
fun PageNumberBar(
    currentPage: Int,
    totalPages: Int,
    onPageSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        // Previous button
        IconButton(onClick = { if (currentPage > 0) onPageSelected(currentPage - 1) }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Previous")
        }

        // Page numbers
        for (page in 0 until totalPages) {
            val isSelected = page == currentPage
            Text(
                text = (page + 1).toString(),
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .clickable { onPageSelected(page) }
                    .then(if (isSelected) Modifier.padding(4.dp) else Modifier),
                style = if (isSelected) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.bodyMedium
            )
        }

        // Next button
        IconButton(onClick = { if (currentPage < totalPages - 1) onPageSelected(currentPage + 1) }) {
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Next")
        }
    }
}


@Composable
fun ProductListItem(product: Product, onItemClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick() }
    ) {
        Image(
            painter = rememberAsyncImagePainter(product.thumbnail),
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
