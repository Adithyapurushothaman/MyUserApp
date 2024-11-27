package com.example.myuserapp

import UserViewModelFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.myuserapp.ui.composable.AppNavHost
import com.example.myuserapp.ui.viewmodel.ProductViewModel
import com.example.myuserapp.ui.viewmodel.ProductViewModelFactory
import com.example.myuserapp.ui.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {

    private val productViewModel: ProductViewModel by viewModels {
        ProductViewModelFactory()
    }
    private val userViewModel: UserViewModel by viewModels{
        UserViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                AppNavHost(userViewModel,productViewModel)
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

