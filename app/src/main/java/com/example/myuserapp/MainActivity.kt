package com.example.myuserapp

import AppNavHost
import com.example.myuserapp.ui.viewmodel.ProductViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.myuserapp.ui.viewmodel.ProductViewModelFactory

class MainActivity : ComponentActivity() {

    private val productViewModel: ProductViewModel by viewModels {
        ProductViewModelFactory()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                AppNavHost(viewModel = productViewModel)
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MaterialTheme {
        content()
    }
}

