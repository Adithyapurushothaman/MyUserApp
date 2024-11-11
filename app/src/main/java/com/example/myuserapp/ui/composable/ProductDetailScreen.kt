import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myuserapp.data.models.ProductDetail
import kotlinx.coroutines.launch
import retrofit2.Response

@Composable
fun ProductDetailScreen(productId: Int, viewModel: ProductViewModel) {
    val productDetailResponse = remember { mutableStateOf<Response<ProductDetail>?>(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(productId) {
        scope.launch {
            productDetailResponse.value = viewModel.getProductDetails(productId)
        }
    }

                ) {
                    val painter = rememberAsyncImagePainter(productDetail.thumbnail)
                    Image(
                        painter = painter,
                        contentDescription = productDetail.description,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp) // Adjust image height as necessary
                            .padding(bottom = 16.dp),
                        contentScale = ContentScale.Crop
                    )

                    Card(
                        modifier = Modifier
                            .padding(10.dp).align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            productDetail.title,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineSmall,
                        )
                        Text(
                            "Price: $${productDetail.price}",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(productDetail.description, style = MaterialTheme.typography.bodyMedium)
                    }

                }
            } ?: Text("Product details are unavailable.")
        } else {
            Text("Failed to load product details: ${response.message()}", color = MaterialTheme.colorScheme.error)
        }
    } ?: CircularProgressIndicator(modifier = Modifier.padding(16.dp))
}
