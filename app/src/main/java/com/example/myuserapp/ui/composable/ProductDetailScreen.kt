import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.myuserapp.data.models.ProductDetail
import com.example.myuserapp.ui.viewmodel.ProductViewModel
import kotlinx.coroutines.launch

@Composable
fun ProductDetailScreen(productId: Int, viewModel: ProductViewModel) {
    val productDetailResponse = remember { mutableStateOf<ProductDetail?>(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(productId) {
        scope.launch {
            productDetailResponse.value = viewModel.getProductDetails(productId)
        }
    }
    if (productDetailResponse.value == null) {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    } else {
        val productDetail = productDetailResponse.value
        if (productDetail != null) {
            Column(
                Modifier
                    .padding(8.dp).fillMaxSize()

            ) {
                Card(
                    modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally),

                ) {
                    val painter = rememberAsyncImagePainter(productDetail.thumbnail)
                    Image(
                        painter = painter,
                        contentDescription = productDetail.description,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
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
            }
        } else {
            Text("Product details are unavailable.")
        }
    }
}


