import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myuserapp.ui.composable.ProductListScreen

@Composable
fun AppNavHost(viewModel: ProductViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "productList") {
        composable("productList") {
            ProductListScreen(viewModel, navController)
        }
        composable("productDetail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull()
            productId?.let { ProductDetailScreen(it, viewModel) }
        }
    }
}
