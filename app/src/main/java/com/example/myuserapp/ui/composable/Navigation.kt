package com.example.myuserapp.ui.composable

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myuserapp.ui.viewmodel.ProductViewModel
import com.example.myuserapp.ui.viewmodel.UserViewModel

@Composable
fun AppNavHost(
    userViewModel: UserViewModel,
    productViewModel: ProductViewModel
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "login") {
        composable("login") {
            LoginScreen(userViewModel,navController)
            { navController.navigate("productList") }
        }
        composable("signup") {
            SignupScreen(userViewModel,navController) { navController.navigate("login") }
        }
        composable("productList") {
            ProductListScreen(productViewModel, navController)
        }
        composable("productDetail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull()
            productId?.let { ProductDetailScreen(it, productViewModel) }
        }
    }
}

